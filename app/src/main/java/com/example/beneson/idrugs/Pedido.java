package com.example.beneson.idrugs;

import java.util.Date;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Pedido {

    private Ofertado ofertado;
    private int qtd;

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

    public Pedido() {
    }

    public Ofertado getOfertado() {
        return ofertado;
    }

    public void setOfertado(Ofertado ofertado) {
        this.ofertado = ofertado;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
