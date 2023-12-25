package com.example.gestionstock;

public class UserModel {
    private int id_user;
    private String nome;
    private String email;
    private String tel;

    public UserModel(int id_user, String nome, String email, String tel) {
        this.id_user = id_user;
        this.nome = nome;
        this.email = email;
        this.tel = tel;
    }

    public UserModel(String nome, String email, String tel) {
        this.nome = nome;
        this.email = email;
        this.tel = tel;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
