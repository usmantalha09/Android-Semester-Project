package com.myfirstapp.mrpc.phc_project.PersonModel;

/**
 * Created by Mr.PC on 4/19/2020.
 */

public class ComplainsModel {
    private String p_name,p_group,p_cnic,p_phoneno,p_address,p_email,p_complain;

    public ComplainsModel(String p_name, String p_group, String p_cnic, String p_phoneno, String p_address, String p_email, String p_complain) {
        this.p_name = p_name;
        this.p_group = p_group;
        this.p_cnic = p_cnic;
        this.p_phoneno = p_phoneno;
        this.p_address = p_address;
        this.p_email = p_email;
        this.p_complain = p_complain;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_group() {
        return p_group;
    }

    public void setP_group(String p_group) {
        this.p_group = p_group;
    }

    public String getP_cnic() {
        return p_cnic;
    }

    public void setP_cnic(String p_cnic) {
        this.p_cnic = p_cnic;
    }

    public String getP_phoneno() {
        return p_phoneno;
    }

    public void setP_phoneno(String p_phoneno) {
        this.p_phoneno = p_phoneno;
    }

    public String getP_address() {
        return p_address;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public String getP_complain() {
        return p_complain;
    }

    public void setP_complain(String p_complain) {
        this.p_complain = p_complain;
    }
}
