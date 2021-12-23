package forTrip;

import exceptions.IncorrectIdInput;
import exceptions.ExistenceException;

import java.util.Scanner;

public class Dispatcher {

    public Trip newTrip(int id, int carId, int driverId) throws ExistenceException, IncorrectIdInput {

        if (Trip.getTripWithId(id) != null) {
            throw new IncorrectIdInput("Введите другой айди,данный уже занят");
        } else {
            if (!Trip.getCarWithId(carId).isBusyCar() && !Trip.getDriverWithId(driverId).isBusyDriver()) {

                Driver driver = Trip.getDriverWithId(driverId);
                Cars car = Trip.getCarWithId(carId);

                Trip trip = new Trip(id, driver, car);

                Trip.getDriverWithId(driverId).setBusyDriver(true);
                Trip.getCarWithId(carId).setBusyCar(true);

                return trip;
            } else {
                System.out.println("Авто или водитель уже заняты");
            }
            return null;
        }
    }

    public void deleteTrip() throws ExistenceException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите id");
        int id = in.nextInt();
        Trip.deleteTrip(id);


    }

}
