package com.example.beneson.idrugs;

import com.backendless.geo.GeoPoint;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Beneson on 09/04/2016.
 */
public class Pedidos {

    private Usuario usuario;
    private List<Pedido> pedidos;
    private String formaPagamento;
    private GeoPoint enderecoEntrega;
    private String formaEntrega;
    private boolean foiEntregue;
    private boolean foiSucesso;
    private Time tempoEntrega;


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

    public Pedidos() {
    }

    public GeoPoint getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(GeoPoint enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public boolean isFoiEntregue() {
        return foiEntregue;
    }

    public void setFoiEntregue(boolean foiEntregue) {
        this.foiEntregue = foiEntregue;
    }

    public boolean isFoiSucesso() {
        return foiSucesso;
    }

    public void setFoiSucesso(boolean foiSucesso) {
        this.foiSucesso = foiSucesso;
    }

    public String getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(String formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Time getTempoEntrega() {
        return tempoEntrega;
    }

    public void setTempoEntrega(Time tempoEntrega) {
        this.tempoEntrega = tempoEntrega;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
