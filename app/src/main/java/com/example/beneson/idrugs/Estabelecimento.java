package com.example.beneson.idrugs;

import com.backendless.geo.GeoPoint;

import java.util.Date;
import java.util.List;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Estabelecimento {

    private String nomeEstabelecimento;
    private String cnpj;
    private List<String> formasEntrega;
    private List<String> formasPagamento;
    private List<Proprietario> proprietario;
    private float velocidade;
    private GeoPoint geolocaliacao;

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

    public Estabelecimento() {
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<String> getFormasEntrega() {
        return formasEntrega;
    }

    public void setFormasEntrega(List<String> formasEntrega) {
        this.formasEntrega = formasEntrega;
    }

    public List<String> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public List<Proprietario> getProprietario() {
        return proprietario;
    }

    public void setProprietario(List<Proprietario> proprietario) {
        this.proprietario = proprietario;
    }

    public GeoPoint getGeolocaliacao() {
        return geolocaliacao;
    }

    public void setGeolocaliacao(GeoPoint geolocaliacao) {
        this.geolocaliacao = geolocaliacao;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }
}
