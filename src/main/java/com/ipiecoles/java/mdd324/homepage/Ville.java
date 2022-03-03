package com.ipiecoles.java.mdd324.homepage;

public class Ville {

    String city;

    public Ville() {

    }

    public Ville(String ville) {
        this.city = ville;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ville{");
        sb.append("city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
