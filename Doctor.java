package serviceStaff;

import exceptions.NotReadyException;
import forTrip.Trip;

public class Doctor {

    public static boolean checkDriver(Trip trip) throws NotReadyException {
        if (trip.getDriver().isReadyDriverToTrip()) {
            trip.setDoctorsMark(true);
            return true;
        } else {
            throw new NotReadyException("Водитель не готов к поездке");
        }
    }
}
