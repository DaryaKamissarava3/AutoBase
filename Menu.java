package menu;

import exceptions.ExistenceException;
import exceptions.NotReadyException;
import exceptions.IncorrectIdInput;
import exceptions.NotFlightCreation;
import forTrip.Cars;
import forTrip.Driver;
import forTrip.Trip;
import forTrip.Dispatcher;
import serviceStaff.Technician;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Menu {

    private static void MenuCars() throws FileNotFoundException, IncorrectIdInput, ExistenceException {
        int option2 = 0;
        while (option2 != 3) {
            System.out.println(
                    " 1.Просмотреть все авто  " +
                            " 2.Добавить авто  " +
                            " 3.Выход "
            );
            int numberMenu2;
            Scanner in = new Scanner(System.in);
            numberMenu2 = in.nextInt();

            switch (numberMenu2) {
                case 1 -> {
                    System.out.println("Просмотреть все авто... \n");
                    Trip.showAllCars();
                }
                case 2 -> {
                    System.out.println("Добавить авто...");
                    Cars car2 = new Cars();
                    car2.addCar();
                }

                case 3 -> {
                    System.out.println("Выход из раздела.");
                    option2 = numberMenu2;
                }
                default -> System.err.println("Введено ложное значение.");
            }
        }
    }

    private static void MenuDrivers() throws FileNotFoundException, IncorrectIdInput, ExistenceException {
        int option2 = 0;
        while (option2 != 4) {
            System.out.println(
                    " 1.Просмотреть всех водителей  " +
                            " 2.Добавить Водителя  " +
                            " 3.Сделать заявку на ремонт авто  " +
                            " 4.Выход "
            );
            int numberMenu2;
            Scanner in = new Scanner(System.in);
            numberMenu2 = in.nextInt();

            switch (numberMenu2) {
                case 1 -> {
                    System.out.println("Список водителей... \n");
                    Trip.showAllDrivers();
                }
                case 2 -> {
                    System.out.println("Добавить водителя...");
                    Driver driver2 = new Driver();
                    driver2.addDriver();
                }
                case 3 -> {
                    System.out.println("Заявка на ремонт...");
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("Введите айди авто");
                    int id_car = in2.nextInt();
                    System.out.println("Введите айди водителя");
                    int id_driver = in2.nextInt();
                    Driver driver = new Driver();
                    driver.repairRequest(id_car, id_driver);
                }
                case 4 -> {
                    System.out.println("Выход из раздела.");
                    option2 = numberMenu2;
                }
                default -> System.err.println("Введено ложное значение.");
            }
        }
    }

    private static void MenuTrip() throws FileNotFoundException, NotReadyException, NotFlightCreation, ExistenceException, IncorrectIdInput {
        int option2 = 0;
        while (option2 != 4) {
            System.out.println(
                    " 1.Просмотреть все рейсы  " +
                            " 2.Сформировать рейс  " +
                            " 3.Удалить рейс  " +
                            " 4.Выход "
            );
            int numberMenu2;
            Scanner in = new Scanner(System.in);
            numberMenu2 = in.nextInt();

            switch (numberMenu2) {
                case 1 -> {
                    System.out.println("Список рейсов... \n");
                    Trip.showAllTrips();
                }
                case 2 -> {
                    System.out.println("Добавить рейс...");
                    Trip.showAllDrivers();
                    Trip.showAllCars();
                    Scanner in2 = new Scanner(System.in);
                    System.out.println("Введите айди рейса");
                    int id_trip = in2.nextInt();
                    System.out.println("Введите айди авто");
                    int id_car = in2.nextInt();
                    System.out.println("Введите айди водителя");
                    int id_driver = in2.nextInt();
                    Dispatcher dispatcher = new Dispatcher();
                    Technician.makeTripActive(dispatcher.newTrip(id_trip, id_car, id_driver));
                }
                case 3 -> {
                    System.out.println("Удалить рейс.");
                    Dispatcher dispatcher = new Dispatcher();
                    dispatcher.deleteTrip();
                }
                case 4 -> {
                    System.out.println("Выход из раздела.");
                    option2 = numberMenu2;
                }
                default -> System.err.println("Введено ложное значение.");
            }
        }
    }

    public static void mainMenu() {
        try {
            System.out.println("\t***Добро пожаловать***\t");
            int option = 0;
            while (option != 4) {
                System.out.println(
                        "1.Действия с авто " +
                                " 2.Действия с водителями " +
                                " 3.Рейсы " +
                                " 4.Выход"
                );
                int numberMenu;
                Scanner in = new Scanner(System.in);
                numberMenu = in.nextInt();

                switch (numberMenu) {
                    case 1 -> {
                        System.out.println("Действия с авто... \n");
                        MenuCars();
                    }
                    case 2 -> {
                        System.out.println("Действия с водителями...");
                        MenuDrivers();
                    }
                    case 3 -> {
                        System.out.println("Рейсы...");
                        MenuTrip();
                    }
                    case 4 -> {
                        System.out.println("Выход из приложения.");
                        option = numberMenu;
                        System.exit(4);
                    }
                    default -> System.err.println("Введено ложное значение.");
                }
            }
        } catch (ExistenceException ex) {
            System.out.println(ex.getMessage());
        } catch (NotReadyException ex) {
            System.out.println(ex.getMessage());
        } catch (IncorrectIdInput ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            Menu.mainMenu();
        }

    }

}
