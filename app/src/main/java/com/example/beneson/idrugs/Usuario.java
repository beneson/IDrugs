package com.example.beneson.idrugs;

import com.backendless.geo.GeoPoint;

import java.util.Date;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Usuario {

    private Users user;
    private GeoPoint geolocalizacao;

    public Usuario() {
    }

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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public GeoPoint getGeolocalizacao() {
        return geolocalizacao;
    }

    public void setGeolocalizacao(GeoPoint geolocalizacao) {
        this.geolocalizacao = geolocalizacao;
    }
}
