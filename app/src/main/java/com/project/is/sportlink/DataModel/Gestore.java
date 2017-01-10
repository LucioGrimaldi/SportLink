package com.project.is.sportlink.DataModel;

/**
 * Created by Mario on 10/01/2017.
 */

public class Gestore {
    /**
     * Item id
     */
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    /**
     * Item nome_u
     */
    @com.google.gson.annotations.SerializedName("nome_g")
    private String mNome;

    /**
     * Item cognome_u
     */
    @com.google.gson.annotations.SerializedName("cognome_g")
    private String mCognome;

    /**
     * Item email_u
     */
    @com.google.gson.annotations.SerializedName("email_g")
    private String mEmail;

    /**
     * Item pass_u
     */
    @com.google.gson.annotations.SerializedName("password_g")
    private String mPass;

    @com.google.gson.annotations.SerializedName("version")
    private String mVersion;

    @com.google.gson.annotations.SerializedName("createdAt")
    private String mCreatedAt;

    @com.google.gson.annotations.SerializedName("updatedAt")
    private String mUpdatedAt;

    @com.google.gson.annotations.SerializedName("deleted")
    private String mDeleted;


    /**
     * Gestore constructor
     */
    public Gestore(){

    }

    /**
     * Initializes a new Gestore
     * @param mEmail l'email dell'utente
     * @param mNome Il nome dell'utente
     * @param mCognome Il cognome dell'utente
     * @param mPass La password dell'utente
     */
    public Gestore(String mId, String mEmail, String mNome, String mCognome, String mPass) {
        this.setmId(mId);
        this.setmEmail(mEmail);
        this.setmNome(mNome);
        this.setmCognome(mCognome);
        this.setmPass(mPass);
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

    public String getmCognome() {
        return mCognome;
    }

    public void setmCognome(String mCognome) {
        this.mCognome = mCognome;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
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
