package ru.ifmo.se.lab5;
public class Main {
    private static String fileName;
    public static void main(String[] args){
        System.out.println("-----------------add pharmacy");
        if(args.length <= 0){
            System.out.println(" No arguments :(");
            //return;
        }
        else {
            fileName = args[0];
        }
        addPharmacy();
    }

    private static void addPharmacy() {
        AddPharmacy addPharmacy = new AddPharmacy();
        addPharmacy.startAdd(fileName);
    }

}
