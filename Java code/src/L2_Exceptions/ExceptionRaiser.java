package L2_Exceptions;

import java.util.Random;

public class ExceptionRaiser {

    public static void main(String[] args) {
        /** Создадим dummy массивы строк, из которого случайным образом быудем выбирать значения для нашего двумерного массива */
        final String[] mixedArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "B"};
        final String[] intArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};


        try {
            /** Передаем полностью валидный массив */
            System.out.println("Массив 4х4 в котором только числа");
            System.out.println("Cумма элементов массива равна: " + checkArray(sendArray(intArray, 4, 4))); // Запуск методов
            /** Передаем массив валидной величины, но со стрингами. Несмотря на стринги я попытался создать метод, который все равно проссумирует все преобразованные инты*/
            System.out.println("Массив 4х4 в котором числа и цифры.");
            System.out.println("Cумма элементов массива равна: " + checkArray(sendArray(mixedArray, 4, 4))); // Запуск методов
            /** Передаем невалидный массив */
            System.out.println("Массив 5х5");
            System.out.println("Cумма элементов массива равна: " + checkArray(sendArray(intArray, 5, 5))); // Запуск методов
        } catch (MyArraySizeException exception) {
            System.out.println("Array dimensions are not 4x4. Please choose other.");
        }

    }

    /**
     * Этот метод принимает одномерный массив (либо со стрингами, либо без), а также параметры размерности. Затем метод его заполняет и выводит в консоль
     */
    public static String[][] sendArray(String[] arrayName, int arrX, int arrY) {
        Random rnd = new Random();
        String[][] stringArray = new String[arrX][arrY];
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[i].length; j++) {
                int index = rnd.nextInt(arrayName.length); // реализуем рандомный бег по dummy массиву от 1 элемента до последнего
                stringArray[i][j] = arrayName[index];
                System.out.print(stringArray[i][j] + " | ");
            }
            System.out.println();
        }
        return stringArray;
    }


    /**
     * Этот метод проверяет поданный массив на размерность 4х4, проверяет наличие стрингов и суммирует элементы массива, возвращая значение суммы. При обнаружении стрингов выводит их координаты в массиве.
     */
    public static int checkArray(String[][] myArray) throws MyArraySizeException, NumberFormatException {  // Замечу, что если создать MyArrayDataException как класс и заэкстендиться от NumberFormatException магии не произойдет, я проверял (со всеми родителями вплоть до RuntimeException) =)
        int base = 0;
        int temp = 0;
        if (myArray.length != 4) {
            throw new MyArraySizeException("Array size is not 4x4!!! Try another one!");
        }
        for (int i = 0; i < myArray.length; i++) {
            for (int j = 0; j < myArray[i].length; j++) {
                try {
                    temp = Integer.parseInt(myArray[i][j]);
                } catch (NumberFormatException exception) {
                    System.out.println("Array contains strings at X: " + (i + 1) + " Y: " + (j + 1));
                }
                base += temp;
            }
        }
        return base;
    }


}
