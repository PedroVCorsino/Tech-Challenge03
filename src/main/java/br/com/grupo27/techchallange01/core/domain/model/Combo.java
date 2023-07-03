package br.com.grupo27.techchallange01.core.domain.model;

import java.math.BigDecimal;

import br.com.grupo27.techchallange01.core.application.dto.AcompanhamentoDTO;
import br.com.grupo27.techchallange01.core.application.dto.BebidaDTO;
import br.com.grupo27.techchallange01.core.application.dto.LancheDTO;
import br.com.grupo27.techchallange01.core.application.dto.SobremesaDTO;

public class Combo {
    private Long id;
    private Lanche lanche;
    private Acompanhamento acompanhamento;
    private Bebida bebida;
    private Sobremesa sobremesa;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    public Combo(Long id, Lanche lanche, Acompanhamento acompanhamento, Bebida bebida, Sobremesa sobremesa,
                 Integer quantidade) {
        this.id = id;
        this.lanche = lanche;
        this.acompanhamento = acompanhamento;
        this.bebida = bebida;
        this.sobremesa = sobremesa;
        this.quantidade = quantidade;
        this.valorUnitario = calcularValorUnitario();
        this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public Combo(Long id2, LancheDTO lanche2, AcompanhamentoDTO acompanhamento2, BebidaDTO bebida2,
            SobremesaDTO sobremesa2, Integer quantidade2) {
    }

    public Combo(Long id2, LancheDTO lanche2, AcompanhamentoDTO acompanhamento2, BebidaDTO bebida2,
            SobremesaDTO sobremesa2, Integer quantidade2, BigDecimal valorUnitario2, BigDecimal valorTotal2) {
    }

    public Combo(Long id2, Lanche dtoToDomain, Acompanhamento dtoToDomain2, Bebida dtoToDomain3, Sobremesa dtoToDomain4,
            Integer quantidade2, BigDecimal valorUnitario2, BigDecimal valorTotal2) {
    }

    private BigDecimal calcularValorUnitario() {
        BigDecimal valorLanche = (lanche != null) ? lanche.getPreco() : BigDecimal.ZERO;
        BigDecimal valorAcompanhamento = (acompanhamento != null) ? acompanhamento.getPreco() : BigDecimal.ZERO;
        BigDecimal valorBebida = (bebida != null) ? bebida.getPreco() : BigDecimal.ZERO;
        BigDecimal valorSobremesa = (sobremesa != null) ? sobremesa.getPreco() : BigDecimal.ZERO;

        return valorLanche.add(valorAcompanhamento).add(valorBebida).add(valorSobremesa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lanche getLanche() {
        return lanche;
    }

    public void setLanche(Lanche lanche) {
        this.lanche = lanche;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    public void setAcompanhamento(Acompanhamento acompanhamento) {
        this.acompanhamento = acompanhamento;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public Sobremesa getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(Sobremesa sobremesa) {
        this.sobremesa = sobremesa;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        this.valorUnitario = calcularValorUnitario();
        this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
