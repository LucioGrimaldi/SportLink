package com.project.is.sportlink.DataModel;

/**
 * Created by Mario on 18/01/2017.
 */

public class Prenotazione {
    /**
     * Item id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Item nome_c
     */
    @com.google.gson.annotations.SerializedName("data_p")
    private String mData_p;

    /**
     * Item sport_c
     */
    @com.google.gson.annotations.SerializedName("orario")
    private String mOrario;

    /**
     * Item nome_s
     */
    @com.google.gson.annotations.SerializedName("FK_utente")
    private String mFK_utente;

    @com.google.gson.annotations.SerializedName("FK_campo")
    private String mFK_campo;

    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;

    @com.google.gson.annotations.SerializedName("createdAt")
    private String mCreatedAt;

    @com.google.gson.annotations.SerializedName("updatedAt")
    private String mUpdatedAt;

    @com.google.gson.annotations.SerializedName("deleted")
    private String mDeleted;


    /**
     * Struttura constructor
     */
    public Prenotazione(){

    }

    public Prenotazione(String mId, String mData_p, String mOrario,String mFK_campo,String mFK_utente) {
        this.setmId(mId);
        this.setmData_p(mData_p);
        this.setmOrario(mOrario);
        this.setmFK_campo(mFK_campo);
        this.setmFK_utente(mFK_utente);
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmData_p() {
        return mData_p;
    }

    public void setmData_p(String mData_p) {
        this.mData_p = mData_p;
    }

    public String getmOrario() {
        return mOrario;
    }

    public void setmOrario(String mOrario) {
        this.mOrario = mOrario;
    }

    public String getmFK_utente() {
        return mFK_utente;
    }

    public void setmFK_utente(String mFK_utente) {
        this.mFK_utente = mFK_utente;
    }

    public String getmFK_campo() {
        return mFK_campo;
    }

    public void setmFK_campo(String mFK_campo) {
        this.mFK_campo = mFK_campo;
    }

    public String getmVersion() {
        return mVersion;
    }

    public void setmVersion(String mVersion) {
        this.mVersion = mVersion;
    }

    public String getmCreatedAt() {
        return mCreatedAt;
    }

    public void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public void setmUpdatedAt(String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }

    public String getmDeleted() {
        return mDeleted;
    }

    public void setmDeleted(String mDeleted) {
        this.mDeleted = mDeleted;
    }
}
