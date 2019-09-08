package ru.ifmo.se.lab5;
public class Main {
    public static void main(String[] args){
        System.out.println("-----------------add pharmacy");
        addPharmacy();
    }

    private static void addPharmacy() {
        AddPharmacy addPharmacy = new AddPharmacy();
        addPharmacy.startAdd();
    }

}
