package com.project.is.sportlink.dataModel;

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

    /**
     * Item nome_s
     */
    @com.google.gson.annotations.SerializedName("nome_s")
    private String mNome_s;

    @com.google.gson.annotations.SerializedName("FK_struttura")
    private String mFK_struttura;

    /**
     * Item indirizzo_s
     */
    @com.google.gson.annotations.SerializedName("indirizzo_s")
    private String mIndirizzo_s;

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
    public Campo(String mId, String mNome, String mSport,String mFK_struttura,String mNome_s,String mIndirizzo_s) {
        this.setmId(mId);
        this.setmNome(mNome);
        this.setmSport(mSport);
        this.setmFK_struttura(mFK_struttura);
        this.setmNome_s(mNome_s);
        this.setmIndirizzo_s(mIndirizzo_s);
    }

    public String getmIndirizzo_s() {
        return mIndirizzo_s;
    }

    public void setmIndirizzo_s(String mIndirizzo_s) {
        this.mIndirizzo_s = mIndirizzo_s;
    }

    public String getmNome_s() {
        return mNome_s;
    }

    public void setmNome_s(String mNome_s) {
        this.mNome_s = mNome_s;
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
