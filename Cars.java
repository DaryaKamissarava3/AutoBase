package forTrip;

import exceptions.IncorrectIdInput;
import exceptions.ExistenceException;
import workWithFiles.FileHandler;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cars {

    private int id;
    private String mark;
    private String model;
    private boolean functioning;
    private boolean isBusyCar;
    static ArrayList<Cars> cars = new ArrayList<Cars>();

    public Cars(int id, String mark, String model, boolean malfunctions, boolean isBusy) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.functioning = malfunctions;
        this.isBusyCar = isBusy;
    }

    public Cars() throws FileNotFoundException {
        FileHandler.getInfoFromFile("Cars.txt");
    }

    public void addCar() throws IncorrectIdInput, ExistenceException {

        Scanner in = new Scanner(System.in);
        System.out.println("Введите id");
        int id = in.nextInt();

        if (Trip.getCarWithId(id) != null) {
            throw new IncorrectIdInput("Введите другой айди,данный уже занят");
        } else {
            this.id = id;
            System.out.println("Введи марку");
            this.mark = in.next();

            System.out.println("Введи серию авто");
            this.model = in.next();

            System.out.println("Функционирует ли авто");
            this.functioning = Boolean.parseBoolean(in.next());
            System.out.println("Занято ли авто");
            this.isBusyCar = Boolean.parseBoolean(in.next());

            cars.add(new Cars(id, mark, model, functioning, isBusyCar));
            FileHandler.writeCar("Cars.txt", id, mark, model, functioning, isBusyCar);
            Trip.addCarToList(new Cars(id, mark, model, functioning, isBusyCar));
        }
    }

    @Override
    public String toString() {
        return "Cars{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", functioning=" + functioning +
                '}';
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setFunctioning(boolean functioning) {
        this.functioning = functioning;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBusyCar(boolean busy) {
        isBusyCar = busy;
    }

    public boolean isBusyCar() {
        return isBusyCar;
    }

    public int getId() {
        return id;
    }

    public boolean isFunctioning() {
        return functioning;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

}
