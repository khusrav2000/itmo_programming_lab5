package ru.ifmo.se.lab5;

public class Pharmacist {
    String name, latsName;

    public Pharmacist(String name, String latsName) {
        this.name = name;
        this.latsName = latsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatsName() {
        return latsName;
    }

    public void setLatsName(String latsName) {
        this.latsName = latsName;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "name='" + name + '\'' +
                ", latsName='" + latsName + '\'' +
                '}';
    }
}
