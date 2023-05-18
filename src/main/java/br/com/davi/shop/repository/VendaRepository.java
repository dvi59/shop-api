package br.com.davi.shop.repository;

import br.com.davi.shop.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v from Venda v where v.vendedor.id = :id and v.dataVenda between :dataInicio and :dataFinal ")
    List<Venda> buscaVendasPorPeriodo(@Param("id") Long id,@Param("dataInicio") Date dataInicio,@Param("dataFinal") Date dataFinal);

    @Query("SELECT v FROM Venda v WHERE v.dataVenda BETWEEN :dataInicio AND :dataFinal")
    List<Venda> buscarVendasPorPeriodo1(
            @Param("dataInicio") Date dataInicio,
            @Param("dataFinal") Date dataFinal
    );

}
