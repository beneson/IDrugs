package com.example.beneson.idrugs;

import java.util.Date;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Ofertado {

    private Produto produto;
    private Estabelecimento estabelecimento;
    private float preco;
    private float qtdApresentacao;
    private String formaFarmaceutica;
    private String apresentacao;
    private int estoqueApresentacao;

    private String objectId;
    public String getObjectId() {
        return objectId;
    }

    private Date created;
    public Date getCreated(){
        return created;
    }

    private Date updated;
    public Date getUpdated(){
        return updated;
    }

    public Ofertado() {
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
    }

    public float getQtdApresentacao() {
        return qtdApresentacao;
    }

    public void setQtdApresentacao(float qtdApresentacao) {
        this.qtdApresentacao = qtdApresentacao;
    }

    public String getApresentacao() {
        return apresentacao;
    }

    public void setApresentacao(String apresentacao) {
        this.apresentacao = apresentacao;
    }

    public int getEstoqueApresentacao() {
        return estoqueApresentacao;
    }

    public void setEstoqueApresentacao(int estoqueApresentacao) {
        this.estoqueApresentacao = estoqueApresentacao;
    }
}
