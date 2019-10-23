package com.aca.springboot.entities;

public class Administrator {
    private String adm_id;
    private String adm_pwd;
    private String adm_name;

    public String getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(String adm_id) {
        this.adm_id = adm_id;
    }

    public String getAdm_pwd() {
        return adm_pwd;
    }

    public void setAdm_pwd(String adm_pwd) {
        this.adm_pwd = adm_pwd;
    }

    public String getAdm_name() {
        return adm_name;
    }

    public void setAdm_name(String adm_name) {
        this.adm_name = adm_name;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "adm_id='" + adm_id + '\'' +
                ", adm_pwd='" + adm_pwd + '\'' +
                ", adm_name='" + adm_name + '\'' +
                '}';
    }
}
