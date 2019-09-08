package ru.ifmo.se.lab5;
import java.util.Vector;

public class Pharmacy{
    Pharmacist pharmacist;
    //TODO: add field medicaments with type array

    Vector<Medicament> medicaments = new Vector<>();
    String address;


    public void setPharmacist(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
    }

    public Pharmacist getPharmacist(){
        return pharmacist;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }

    public void setMedicaments(Vector<Medicament> medicaments){
        this.medicaments = medicaments;
    }

    public Vector<Medicament> getMedicaments(){
        return medicaments;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "pharmacist=" + pharmacist +
                ", medicaments=" + medicaments +
                ", address='" + address + '\'' +
                '}';
    }
}
