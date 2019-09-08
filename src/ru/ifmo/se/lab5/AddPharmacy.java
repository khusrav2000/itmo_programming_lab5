package ru.ifmo.se.lab5;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

class AddPharmacy {

    private static Scanner scan = new Scanner(System.in);
    private static Scanner sc = new Scanner(System.in);
    private final String JSON_FILE_NAME = "C:\\Users\\KHUSRAV\\Documents\\Programming\\Java\\LabsItmo\\ProgLab5_2\\src\\ru\\ifmo\\se\\lab5\\Pharmacies.json"; //Путь к Json файлу
    Vector<Pharmacy> pharmacies = new Vector<>();

    void startAdd() {
        for (;;) {
            // команда которая приходить из командной строки
            String command = scan.nextLine();
            String load = "^load$";
            String removeElement = "^remove\\s\\{.*[:]{1}.*[,].*[:]{1}.*[,].*[:]{1}.*[,].*[:]{1}.*\\}$";
            String info = "^info$";
            String addElement = "^add\\s\\{.*[:]{1}.*[,].*[:]{1}.*[,].*[:]{1}.*[,].*[:]{1}.*\\}$";
            String removeIndex = "^remove\\s\\{\\d*\\}";
            String show = "^show$";
            String remove_first = "^remove_first$";
            String update = "^update$";
            if (Pattern.compile(load).matcher(command).find()) {
                System.out.println("load");
                load();
            }
            if (Pattern.compile(show).matcher(command).find()){
                //getCollaction
            }
            System.out.println(getSizeOfPharmacies());;
        }
    }

    private void load() {

        try {
            sc = new Scanner(new File(JSON_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("----");
        }
        StringBuilder jsonData = new StringBuilder();
        while (sc.hasNext()){
            jsonData.append(sc.next());
        }

        JsonParser jsonParser = new JsonParser();
        System.out.println(jsonData.toString());
        JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonData.toString());
        System.out.println(jsonArray);

        Iterator<JsonElement> iterator = jsonArray.iterator();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        while (iterator.hasNext()){
            JsonObject jsonObject = (JsonObject) iterator.next();

            System.out.println();

            Pharmacy pharmacy = gson.fromJson(jsonObject, Pharmacy.class);
            pharmacies.add(pharmacy);
        }

    }

    private int getSizeOfPharmacies(){
        System.out.println(pharmacies.get(0).toString());
        return pharmacies.size();
    }
}
