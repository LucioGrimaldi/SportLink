package com.project.is.sportlink.dataModel;

/**
 * Created by Mario on 11/01/2017.
 */

public class Struttura {
    /**
     * Item id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Item nome_s
     */
    @com.google.gson.annotations.SerializedName("nome_s")
    private String mNome;

    /**
     * Item telefono_s
     */
    @com.google.gson.annotations.SerializedName("telefono_s")
    private String mTelefono;

    /**
     * Item indirizzo_s
     */
    @com.google.gson.annotations.SerializedName("indirizzo_s")
    private String mIndirizzo;

    /**
     * Item città_s
     */
    @com.google.gson.annotations.SerializedName("citta_s")
    private String mCittà;


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
    public Struttura(){

    }

    /**
     * Initializes a new Struttura
     * @param mNome Il nome della struttura
     * @param mTelefono Il numero di telefono della struttura
     * @param mIndirizzo indirizzo della struttura
     * @param mCittà città nella quale si trova la struttura sportiva
     */
    public Struttura(String mId, String mNome, String mTelefono, String mIndirizzo, String mCittà) {
        this.setmId(mId);
        this.setmNome(mNome);
        this.setmTelefono(mTelefono);
        this.setmIndirizzo(mIndirizzo);
        this.setmCittà(mCittà);
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

    public String getmTelefono() {
        return mTelefono;
    }

    public void setmTelefono(String mTelefono) {
        this.mTelefono = mTelefono;
    }

    public String getmIndirizzo() {
        return mIndirizzo;
    }

    public void setmIndirizzo(String mIndirizzo) {
        this.mIndirizzo = mIndirizzo;
    }

    public String getmCittà() {
        return mCittà;
    }

    public void setmCittà(String mCittà) {
        this.mCittà = mCittà;
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
