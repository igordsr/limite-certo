package com.limite_certo.repository;

import com.limite_certo.entity.Pagamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagamentoRepository extends BaseRepository<Pagamento, Long> {
    @Query("SELECT COALESCE(SUM(p.valor), 0)  FROM Pagamento p WHERE p.cartao.numero = :numero AND p.cartao.dataValidade = :dataValidade AND p.cartao.cvv = :cvv")
    Double somarValoresPorCartao(@Param("numero") String numero, @Param("dataValidade") LocalDate dataValidade, @Param("cvv") Integer cvv);

    List<Pagamento> findByCliente_Id(Long id);


}