package br.com.davi.shop.http.controller;

import br.com.davi.shop.dto.VendaDTO;
import br.com.davi.shop.entity.Venda;
import br.com.davi.shop.entity.Vendedor;
import br.com.davi.shop.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
       public VendaDTO concluirVenda(@RequestBody(required = false)VendaDTO vendaDTO, UriComponentsBuilder uriComponentsBuilder) throws Exception{
        return vendaService.finalizarVenda(vendaDTO);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> consultarVendaPorVendedor(
            @PathVariable("id") Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim
    ) throws Exception{
        List<Venda> listVenda = vendaService.consultarVendaPeriodo(id, dataInicio, dataFim);
        int totalVendas = listVenda.size();
        double mediaVendasPorDia = (double) totalVendas / ((double) ( dataFim.getTime() - dataInicio.getTime()) / (1000 * 60 * 60 * 24));

        Map<String, Object> response = new HashMap<>();
        response.put("totalVendas", totalVendas);
        response.put("mediaVendasPorDia", mediaVendasPorDia);
        response.put("listVendas", listVenda);
        return response;
    }
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> consultarVendasAllVendedor(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim
    ){
        Map<String, Object> response = new HashMap<>();
        Map<Vendedor, Double> mediaVendasPorDia = new HashMap<>();
        List<Venda> listVendedor = vendaService.consultarVendasPorVendedor(dataInicio,dataFim);

        Map<Vendedor, Integer> vendasPorVendedor = new HashMap<>();

        for(Venda venda: listVendedor){
            Vendedor vendedor = venda.getVendedor();
            vendasPorVendedor.put(vendedor, vendasPorVendedor.getOrDefault(vendedor,0)+1);
        }
        int dias = calcularDiasEntre(dataInicio, dataFim);
        for (Vendedor vendedor : vendasPorVendedor.keySet()) {
            Integer totalVendas = vendasPorVendedor.get(vendedor);
            Double mediaVendas = (double) totalVendas / dias; // Calcula a média de vendas por dia
            mediaVendasPorDia.put(vendedor, mediaVendas);
        }
        response.put("vendasPorVendedor", vendasPorVendedor);
        response.put("mediaVendasPorDia", mediaVendasPorDia);

        return response;
    }
    private int calcularDiasEntre(Date dataInicio, Date dataFim) {
        long diffInMilliseconds = Math.abs(dataFim.getTime() - dataInicio.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
        return (int) diffInDays + 1; // +1 para incluir o último dia
    }
}
