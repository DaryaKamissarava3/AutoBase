package workWithFiles;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    public static void writeCar(String fileName, int id, String model, String mark, boolean functioning,boolean isBusy) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));) {
            pw.println(id + "," + model + "," + mark + "," + functioning + "," + isBusy);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeDriverToFile(String fileName, int id, String name, String surname, String phoneNumber, boolean readyDriverToTrip,boolean isBusyDriver) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName), true));) {
            pw.println(Integer.toString(id) + "," + name + "," + surname + "," + phoneNumber + "," + Boolean.toString(readyDriverToTrip)+ "," + Boolean.toString(isBusyDriver));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeTripToFile(String fileName, int id, int id_car, int id_driver) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName), true));) {
            pw.println(id + "," + id_car + "," + id_driver );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getInfoFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            ArrayList<String> list = new ArrayList<>();
            String s;
            while ((s = br.readLine()) != null) {
                list.add(s);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(filename + " does not exist.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void rewriteFile(String filename, ArrayList<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String line : list) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void writeLog(String info) {
        String filename = "ForRepair.log";
        String FILENAME = "D:\\Programs\\java\\KursWork\\" + filename;
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(FILENAME, true);
            bw = new BufferedWriter(fw);
            bw.write(info);
            bw.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
