package com.myfirstapp.mrpc.phc_project.PersonModel;

/**
 * Created by Mr.PC on 4/8/2020.
 */

public class DoctorModel {
    private String d_Name,d_Email,d_Pass,d_Phone,d_Address,d_Specialization,d_Description,d_Review,d_Fee,d_Rating,ImageURL;

    public DoctorModel(String d_Name, String d_Email, String d_Pass, String d_Phone, String d_Address, String d_Specialization, String d_Description, String d_Review, String d_Fee, String d_Rating, String imageURL) {
        this.d_Name = d_Name;
        this.d_Email = d_Email;
        this.d_Pass = d_Pass;
        this.d_Phone = d_Phone;
        this.d_Address = d_Address;
        this.d_Specialization = d_Specialization;
        this.d_Description = d_Description;
        this.d_Review = d_Review;
        this.d_Fee = d_Fee;
        this.d_Rating = d_Rating;
        ImageURL = imageURL;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getD_Email() {
        return d_Email;
    }

    public void setD_Email(String d_Email) {
        this.d_Email = d_Email;
    }

    public String getD_Pass() {
        return d_Pass;
    }

    public void setD_Pass(String d_Pass) {
        this.d_Pass = d_Pass;
    }

    public String getD_Phone() {
        return d_Phone;
    }

    public void setD_Phone(String d_Phone) {
        this.d_Phone = d_Phone;
    }

    public String getD_Address() {
        return d_Address;
    }

    public void setD_Address(String d_Address) {
        this.d_Address = d_Address;
    }

    public String getD_Specialization() {
        return d_Specialization;
    }

    public void setD_Specialization(String d_Specialization) {
        this.d_Specialization = d_Specialization;
    }

    public String getD_Description() {
        return d_Description;
    }

    public void setD_Description(String d_Description) {
        this.d_Description = d_Description;
    }

    public String getD_Review() {
        return d_Review;
    }

    public void setD_Review(String d_Review) {
        this.d_Review = d_Review;
    }

    public String getD_Fee() {
        return d_Fee;
    }

    public void setD_Fee(String d_Fee) {
        this.d_Fee = d_Fee;
    }

    public String getD_Rating() {
        return d_Rating;
    }

    public void setD_Rating(String d_Rating) {
        this.d_Rating = d_Rating;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
