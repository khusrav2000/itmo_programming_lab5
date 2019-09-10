package ru.ifmo.se.lab5;

public class Pharmacist {
    String name, lastName;

    public Pharmacist(String name, String latsName) {
        this.name = name;
        this.lastName = latsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatsName() {
        return lastName;
    }

    public void setLatsName(String latsName) {
        this.lastName = latsName;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "name='" + name + '\'' +
                ", latsName='" + lastName + '\'' +
                '}';
    }
}
