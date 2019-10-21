package ru.ifmo.se.lab5;

import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

class AddPharmacy {

    private static Scanner scan = new Scanner(System.in);
    private static Scanner sc = new Scanner(System.in);
    private String JSON_FILE_NAME = "C:\\Users\\KHUSRAV\\Documents\\IntellijIDEAProjects\\itmo_programming_lab5\\src\\ru\\ifmo\\se\\lab5\\Pharmacies.json"; //Путь к Json файлу
    //private final String JSON_FILE_NAME = "/home/s247409/KHUSRAV/itmo_programming_lab5/src/ru/ifmo/se/lab5/Pharmacies.json";
    //private final String JSON_FILE_NAME = "/home/s247409/KHUSRAV/itmo_programming_lab5/src/ru/ifmo/se/lab5/Pharmacies.json";
    //private final String JSON_FILE_NAME = "/home/s247409/KHUSRAV/src/ru/ifmo/se/lab5/Pharmacies.json";
    //private final String JSON_FILE_NAME = "/home/s247409/KHUSRAV/Pharmacies.json";
    //private final String JSON_FILE_NAME = "./Pharmacies.json";
    //private final String JSON_FILE_NAME = "./ru/ifmo/se/lab5/Pharmacies.json";
    File f = new File("test.txt");

    Vector<Pharmacy> pharmacies = new Vector<>();

    void startAdd(String fileName) {
        //JSON_FILE_NAME = fileName;
        for (;;) {
            // команда которая приходить из командной строки
            String command = scan.nextLine();
            String load = "^load$";
            String removeElement = "^remove\\s*\\{\"";
            String info = "^info$";
            String addElement = "^add\\s\\{\"";
            String removeIndex = "^remove\\s\\{\\d*\\}";
            String show = "^show$";
            String remove_first = "^remove_first$";
            String update = "^update$";
            if (Pattern.compile(load).matcher(command).find()) {
                System.out.println("load");
                load();
            } else if (Pattern.compile(show).matcher(command).find()){
                showCollection();
            } else if (Pattern.compile(remove_first).matcher(command).find()){
                deleteFirstElement();
            } else if (Pattern.compile(removeIndex).matcher(command).find()){
                deleteElementByIndex(command);
            } else if (Pattern.compile(removeElement).matcher(command).find()){
                deleteElementByValue(command.substring(7, command.length()));
            } else if (Pattern.compile(addElement).matcher(command).find()){
                addElementToCollection(command.substring(4, command.length()));
            } else {
                System.out.println("Неправилная команда");
            }

        }
    }

    private void addElementToCollection(String element) {

        JsonParser jsonParser = new JsonParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            JsonObject jsonObject = (JsonObject) jsonParser.parse(element.toString());
            Pharmacy pharmacy = gson.fromJson(jsonObject, Pharmacy.class);
            pharmacies.add(pharmacy);
        } catch (Exception e){
            System.out.println("Неправилная команда");
        }

        updateData();
    }

    private void deleteElementByValue(String element) {

        JsonParser jsonParser = new JsonParser();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            JsonObject jsonObject = (JsonObject) jsonParser.parse(element.toString());
            Object pharmacy = gson.fromJson(jsonObject, Pharmacy.class);
            for (int i = 0 ; i < pharmacies.size(); i ++) {
                if (pharmacies.get(i).toString().equals(pharmacy.toString())) {
                    pharmacies.remove(i);
                    i --;
                }
            }
        } catch (Exception e){
            System.out.println("Неправилная команда");
        }
        updateData();
    }

    private void deleteElementByIndex(String stringIndex) {
        //System.out.println(stringIndex.substring(8, stringIndex.length() - 1));
        try {
            int index = Integer.parseInt(stringIndex.substring(8, stringIndex.length() - 1));
            pharmacies.remove(index);
        } catch (Exception e){
            System.out.println("Не удалос удалить элемент");
        }

        updateData();
    }

    private void deleteFirstElement() {
        pharmacies.remove(0);

        updateData();
    }


    private void showCollection() {
        for (Pharmacy pharmacy : pharmacies){
            System.out.println(pharmacy.toString());
        }
    }

    private void load() {
        System.out.println(f.getAbsolutePath());

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

        Iterator<JsonElement> iterator = jsonArray.iterator();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();



        while (iterator.hasNext()){
            JsonObject jsonObject = (JsonObject) iterator.next();

            Pharmacy pharmacy = gson.fromJson(jsonObject, Pharmacy.class);
            pharmacies.add(pharmacy);
        }

        System.out.println("loaded");

    }


    private void updateData() {
        if (pharmacies.size() == 0){
            return;
        }
        try {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            StringBuilder answer = new StringBuilder("");
            for (Pharmacy pharmacy : pharmacies){
                String json = gson.toJson(pharmacy) + ",";
                answer.append(json);
            }
            answer.deleteCharAt(answer.length() - 1);
            answer.insert(0, '[');
            answer.append("]");

            System.out.println(answer);
            BufferedWriter bw = new BufferedWriter(new FileWriter(JSON_FILE_NAME));
            bw.write(answer.toString());
            bw.flush();


        } catch (Exception e){
            System.out.println("Что-то пошло не так!");
        }

    }

    private int getSizeOfPharmacies(){
        return pharmacies.size();
    }
}
