package br.com.davi.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_venda", nullable = false)
    private Date dataVenda;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Vendedor vendedor;


}
