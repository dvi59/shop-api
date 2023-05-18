package br.com.davi.shop.dto;

import br.com.davi.shop.entity.Venda;
import br.com.davi.shop.entity.Vendedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendaDTO
{

    private Long id;
    private Date dataVenda;
    private Double valor;
    private Vendedor vendedor;



    public VendaDTO(Venda venda){
        this.id = venda.getId();
        this.dataVenda = venda.getDataVenda();
        this.valor = venda.getValor();
        this.vendedor = venda.getVendedor();


    }

}
