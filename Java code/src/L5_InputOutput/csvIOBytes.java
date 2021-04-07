package L5_InputOutput;

import java.io.*;

public class csvIOBytes {

    public static void main(String[] args) {
        /** Реализуем массивы данных и организуем хранилище в классе AppData */
        String[] newHeaders = new String[]{"Value_1", "Value_2", "Value_3"};
        int[][] newData = new int[][]{{100, 200, 123}, {300, 400, 500}};
        AppData appData = new AppData(newHeaders, newData);


        /** Самый простой способ - перенос объектов */
        File file1 = new File("file_bytes.csv");
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(file1))) {

            objectOut.writeObject(appData);
            objectOut.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(file1))) {
            AppData appDatain = (AppData) objectIn.readObject();
            System.out.println(appDatain);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}


