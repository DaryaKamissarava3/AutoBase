package forTrip;

import workWithFiles.FileHandler;
import exceptions.ExistenceException;

import java.util.ArrayList;

public class Trip {

    private int id;
    private Cars car;
    private Driver driver;
    private boolean doctorsMark;
    private boolean technicalMark;
    private boolean active;

    static String carsFile = "Cars.txt";
    static String driversFile = "Drivers.txt";
    static String tripsFile = "Trips.txt";

    private static ArrayList<Cars> cars = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Trip> trips = new ArrayList<>();

    static {
        ArrayList<String> list = FileHandler.getInfoFromFile(carsFile);

        for (String s : list) {
            String[] lst = s.split(",");
            cars.add(new Cars(Integer.parseInt(lst[0]), lst[1], lst[2], Boolean.parseBoolean(lst[3]), Boolean.parseBoolean(lst[4])));
        }

    }

    static {
        ArrayList<String> list = FileHandler.getInfoFromFile(driversFile);
        assert list != null;
        for (String s : list) {
            String[] lst = s.split(",");
            drivers.add(new Driver(Integer.parseInt(lst[0]), lst[1], lst[2], lst[3], Boolean.parseBoolean(lst[4]), Boolean.parseBoolean(lst[5])));
        }
    }

    static {
        ArrayList<String> list = FileHandler.getInfoFromFile(tripsFile);
        assert list != null;
        for (String s : list) {
            String[] lst = s.split(",");
            try {
                trips.add(new Trip(Integer.parseInt(lst[0]), getDriverWithId(Integer.parseInt(lst[1])),
                        getCarWithId(Integer.parseInt(lst[2]))));
            } catch (ExistenceException e) {
                e.printStackTrace();
            }
        }
    }

    public Trip(int id, Driver driver, Cars car) {
        this.id = id;
        this.driver = driver;
        this.car = car;
        this.technicalMark = true;
        this.doctorsMark = true;
        this.active = true;
    }

    public static void writeAllTripsToFile() {
        ArrayList<String> lst = new ArrayList<>();
        String str = "";
        for (Trip trip : trips) {
            str = trip.getId() + "," + trip.getCar().getId() +
                    "," + trip.getDriver().getId();
            lst.add(str);
        }
        FileHandler.rewriteFile("Trips.txt", lst);
    }

    public static void addTripToList(Trip trip) {
        trips.add(trip);
        Trip.writeAllTripsToFile();
    }

    public static void rewriteCarsFile() {
        ArrayList<String> lst = new ArrayList<>();
        String str = "";
        for (Cars car : cars) {
            str = car.getId() + "," + car.getModel() +
                    "," + car.getMark() + "," + car.isFunctioning() + "," + car.isBusyCar();
            lst.add(str);
        }
        FileHandler.rewriteFile("Cars.txt", lst);
    }

    public static void addCarToList(Cars car) {
        cars.add(car);
        Trip.rewriteCarsFile();
    }

    public static void rewriteDriversFile() {
        ArrayList<String> lst = new ArrayList<>();
        String str = "";
        for (Driver driver : drivers) {
            str = driver.getId() + "," + driver.getName() +
                    "," + driver.getSurname() + "," + driver.getPhoneNumber() +
                    "," + driver.isReadyDriverToTrip() + "," + driver.isBusyDriver();
            lst.add(str);
        }
        FileHandler.rewriteFile("Drivers.txt", lst);
    }

    public static void addDriversToList(Driver driver) {
        drivers.add(driver);
        Trip.rewriteDriversFile();
    }

    public static void showAllTrips() {
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    public static void showAllDrivers() {
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    public static void showAllCars() {
        for (Cars car : cars) {
            System.out.println(car);
        }
    }

    public static Cars getCarWithId(int carId) throws ExistenceException {
        int car_id = -1;
        for (int i = 0; i < Trip.cars.size(); i++) {
            if (Trip.cars.get(i).getId() == carId) {
                car_id = i;
                break;
            }
        }
        if (car_id != -1) {
            return cars.get(car_id);
        } else {
            return null;
        }
    }

    public static Driver getDriverWithId(int driverId) throws ExistenceException {
        int driver_id = -1;
        for (int i = 0; i < Trip.drivers.size(); i++) {
            if (Trip.drivers.get(i).getId() == driverId) {
                driver_id = i;
            }
        }
        if (driver_id != -1) {
            return drivers.get(driver_id);
        } else {
            return null;
        }
    }

    public static Trip getTripWithId(int tripId) throws ExistenceException {
        int trip_id = -1;
        for (int i = 0; i < Trip.trips.size(); i++) {
            if (Trip.trips.get(i).getId() == tripId) {
                trip_id = i;
            }
        }
        if (trip_id != -1) {
            return trips.get(trip_id);
        } else {
            return null;
        }
    }

    public static void deleteTrip(int id) throws ExistenceException {

        trips.remove(Trip.getTripWithId(id));
        Trip.getDriverWithId(id).setBusyDriver(false);
        Trip.getCarWithId(id).setBusyCar(false);
        writeAllTripsToFile();

    }

    public void setCar(Cars car) {
        this.car = car;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public static void setCars(ArrayList<Cars> cars) {
        Trip.cars = cars;
    }

    public static void setDrivers(ArrayList<Driver> drivers) {
        Trip.drivers = drivers;
    }

    public static void setDriversFile(String driversFile) {
        Trip.driversFile = driversFile;
    }

    public static void setCarsFile(String carsFile) {
        Trip.carsFile = carsFile;
    }

    public void setDoctorsMark(boolean doctorsMark) {
        this.doctorsMark = doctorsMark;
    }

    public void setTechnicalMark(boolean technicalMark) {
        this.technicalMark = technicalMark;
    }

    public void setActive(boolean active) {
        this.active = active;
        writeAllTripsToFile();
    }

    public int getId() {
        return id;
    }

    public static ArrayList<Cars> getCars() {
        return cars;
    }

    public static String getCarsFile() {
        return carsFile;
    }

    public static ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public Driver getDriver() {
        return driver;
    }

    public Cars getCar() {
        return car;
    }

    public static ArrayList<Trip> getTrips() {
        return trips;
    }

    public boolean isDoctorsMark() {
        return doctorsMark;
    }

    public boolean isTechnicalMark() {
        return technicalMark;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id рейса=" + id +
                "  Авто=" + car +
                "  Водитель=" + driver +
                ", doctorsMark=" + doctorsMark +
                ", technicalMark=" + technicalMark +
                ", active=" + active +
                '}';
    }
}
