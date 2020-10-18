package com.myfirstapp.mrpc.phc_project.PersonModel;

/**
 * Created by Mr.PC on 4/8/2020.
 */

public class PatientModel {
    private String pa_Name,pa_Email,pa_Password,pa_Phone,pa_Address,ImageURL;

    public PatientModel(String pa_Name, String pa_Email, String pa_Password, String pa_Phone, String pa_Address, String imageURL) {
        this.pa_Name = pa_Name;
        this.pa_Email = pa_Email;
        this.pa_Password = pa_Password;
        this.pa_Phone = pa_Phone;
        this.pa_Address = pa_Address;
        ImageURL = imageURL;
    }

    public String getPa_Name() {
        return pa_Name;
    }

    public void setPa_Name(String p_Name) {
        this.pa_Name = pa_Name;
    }

    public String getPa_Email() {
        return pa_Email;
    }

    public void setPa_Email(String pa_Email) {
        this.pa_Email = pa_Email;
    }

    public String getPa_Password() {
        return pa_Password;
    }

    public void setPa_Password(String pa_Password) {
        this.pa_Password = pa_Password;
    }

    public String getPa_Phone() {
        return pa_Phone;
    }

    public void setPa_Phone(String pa_Phone) {
        this.pa_Phone = pa_Phone;
    }

    public String getPa_Address() {
        return pa_Address;
    }

    public void setPa_Address(String pa_Address) {
        this.pa_Address = pa_Address;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
