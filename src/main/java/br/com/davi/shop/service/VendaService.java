package br.com.davi.shop.service;

import br.com.davi.shop.dto.VendaDTO;
import br.com.davi.shop.entity.Venda;
import br.com.davi.shop.entity.Vendedor;
import br.com.davi.shop.repository.VendaRepository;
import br.com.davi.shop.repository.VendedorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class VendaService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorRepository vendedorRepository;


    public VendaDTO finalizarVenda(VendaDTO vendaDTO) throws Exception {
        Vendedor vendedor = vendedorRepository.findById(vendaDTO.getVendedor().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não encontrado"));

        Venda venda = new Venda();
        venda.setValor(vendaDTO.getValor());
        venda.setDataVenda(new Date());
        venda.setVendedor(vendedor);
        vendaRepository.save(venda);

        return new VendaDTO(venda);
    }

    public List<Venda> consultarVendaPeriodo(Long id, Date dataInicio, Date dataFinal){
       return vendaRepository.buscaVendasPorPeriodo(id, dataInicio, dataFinal);
    }

    public List<Venda> consultarVendasPorVendedor(Date dataInicio, Date dataFinal){
        return vendaRepository.buscarVendasPorPeriodo1(dataInicio, dataFinal);
    }


}
