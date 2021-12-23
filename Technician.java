package serviceStaff;

import forTrip.Trip;
import exceptions.NotFlightCreation;
import exceptions.NotReadyException;

public class Technician {

    public static boolean checkCar(Trip trip) throws NotReadyException {
        if (trip.getCar().isFunctioning()) {
            trip.setTechnicalMark(true);
            return true;
        } else {
            throw new NotReadyException("Машина не готова к поездке");
        }
    }

    public static void makeTripActive(Trip trip) throws NotReadyException, NotFlightCreation {
        if (Doctor.checkDriver(trip) && Technician.checkCar(trip)) {
            trip.setActive(true);
            System.out.println("Trip is active now");
            Trip.addTripToList(trip);
        } else {
            throw new NotFlightCreation("Рейс не активен");
        }
    }
}
