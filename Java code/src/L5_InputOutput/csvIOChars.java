package L5_InputOutput;

import java.io.*;

public class csvIOChars {

    public static void main(String[] args) {
        /** Реализуем массивы данных и организуем хранилище в классе AppData */
        String[] newHeaders = new String[]{"Value_1", "Value_2", "Value_3"}; // Зададим массив Headers
        int[][] newData = new int[][]{{100, 200, 123}, {300, 400, 500}}; // Зададим массив Data
        AppData appData = new AppData(newHeaders, newData); // тут храним данные, поэтому соберем appdata по конструктору из этих двух массивов

        /** Реализуем перенос через символьные классы Writer/Reader */
        /** Сначала запишем данные в файл */
        File file = new File("file_chars.csv");
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(file))) {
            for (String value : appData.getHeaders()) {
                writter.write(value + " ; ");
            }
            writter.write("\n");
            for (int i = 0; i < appData.getData().length; i++) {
                for (int j = 0; j < appData.getData()[i].length; j++) {
                    writter.write(appData.getData()[i][j] + " ; ");
                }
                writter.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Теперь выводим даннеы из файла в консоль */
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line + " ; ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
