package com.example.beneson.idrugs;

import java.util.Date;

/**
 * Created by Beneson on 03/04/2016.
 */
public class Proprietario extends Usuario {

    private String documentacao;
    private Users user;

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


    public Proprietario() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getDocumentacao() {
        return documentacao;
    }

    public void setDocumentacao(String documentacao) {
        this.documentacao = documentacao;
    }
}
