package com.example.gestionstock;

public class Histitem {
    private int idhist;
    private int id_comp;
    private int id_user;
    private String date;
    private int quantite;

    public int getIdhist() {
        return idhist;
    }

    public void setIdhist(int idhist) {
        this.idhist = idhist;
    }

    public int getId_comp() {
        return id_comp;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Histitem(int idhist, int id_comp, int id_user, int quantite, String date) {
        this.idhist = idhist;
        this.id_comp = id_comp;
        this.id_user = id_user;
        this.date = date;
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Histitem{" +
                "idhist=" + idhist +
                ", id_comp=" + id_comp +
                ", id_user=" + id_user +
                ", date='" + date + '\'' +
                ", quantite=" + quantite +
                '}';
    }

    public Histitem(int id_comp, int id_user, String date, int quantite) {
        this.id_comp = id_comp;
        this.id_user = id_user;
        this.date = date;
        this.quantite = quantite;
    }
}
