package com.project.is.sportlink.DataModel;

/**
 * Created by Mario on 11/01/2017.
 */

public class Campo {
    /**
     * Item id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Item nome_c
     */
    @com.google.gson.annotations.SerializedName("nome_c")
    private String mNome;

    /**
     * Item sport_c
     */
    @com.google.gson.annotations.SerializedName("sport_c")
    private String mSport;


    @com.google.gson.annotations.SerializedName("FK_struttura")
    private String mFK_struttura;


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
    public Campo(){

    }

    /**
     * Initializes a new Campo
     * @param mNome Il nome della struttura
     * @param mSport lo sport che si può praticare nel campo
     */
    public Campo(String mId, String mNome, String mSport,String mFK_struttura) {
        this.setmId(mId);
        this.setmNome(mNome);
        this.setmSport(mSport);
        this.setmFK_struttura(mFK_struttura);
    }

    public String getmFK_struttura() {
        return mFK_struttura;
    }

    public void setmFK_struttura(String mFK_struttura) {
        this.mFK_struttura = mFK_struttura;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public String getmSport() {
        return mSport;
    }

    public void setmSport(String mSport) {
        this.mSport = mSport;
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