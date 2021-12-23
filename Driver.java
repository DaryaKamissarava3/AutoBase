package forTrip;

import exceptions.ExistenceException;
import exceptions.IncorrectIdInput;
import workWithFiles.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Driver {

    private int id;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean readyDriverToTrip;
    private boolean isBusyDriver = false;

    static ArrayList<Driver> drivers = new ArrayList<Driver>();


    public Driver() throws FileNotFoundException {
        FileHandler.getInfoFromFile("Drivers.txt");
    }

    public Driver(int id, String name, String surname, String phoneNumber, boolean readyDriverToTrip, boolean isBusyDriver) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.readyDriverToTrip = readyDriverToTrip;
        this.isBusyDriver = isBusyDriver;
    }

    public void addDriver() throws IncorrectIdInput, ExistenceException {

        Scanner in2 = new Scanner(System.in);
        System.out.println("Введи id");
        int id = in2.nextInt();

        if (Trip.getDriverWithId(id) != null) {
            throw new IncorrectIdInput("Введите другой айди,данный уже занят");
        } else {
            System.out.println("Введи имя водителя");
            this.name = in2.next();
            System.out.println("Введи фамилию");
            this.surname = in2.next();
            System.out.println("Введи телефон");
            this.phoneNumber = in2.next();
            System.out.println("Готов ли водитель");
            this.readyDriverToTrip = Boolean.parseBoolean(in2.next());

            drivers.add(new Driver(id, name, surname, phoneNumber, readyDriverToTrip, isBusyDriver));
            FileHandler.writeDriverToFile("Drivers.txt", id, name, surname, phoneNumber, readyDriverToTrip, isBusyDriver);
            Trip.addDriversToList(new Driver(id, name, surname, phoneNumber, readyDriverToTrip, isBusyDriver));
        }
    }

    public void repairRequest(int driverId, int carId) throws ExistenceException {
        String str = "Водитель " + Objects.requireNonNull(Trip.getDriverWithId(driverId)).getName() +
                " сделал заявку на ремонт " + Objects.requireNonNull(Trip.getCarWithId(carId)).getId() +
                " " + Objects.requireNonNull(Trip.getCarWithId(carId)).getMark()
                + " " + Objects.requireNonNull(Trip.getCarWithId(carId)).getModel() +
                "\n";

        Objects.requireNonNull(Trip.getCarWithId(carId)).setFunctioning(false);
        FileHandler.writeLog(str);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", readyDriverToTrip=" + readyDriverToTrip +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBusyDriver(boolean busyDriver) {
        isBusyDriver = busyDriver;
    }

    public void setReadyDriverToTrip(boolean readyDriverToTrip) {
        this.readyDriverToTrip = readyDriverToTrip;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isReadyDriverToTrip() {
        return readyDriverToTrip;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public boolean isBusyDriver() {
        return isBusyDriver;
    }

    public int getId() {
        return id;
    }
}
