package L3_Generalizations.Task1;

import java.util.Arrays;
import java.util.Scanner;

public class SwapArray<T> {
    private T[] array;


    public SwapArray(T... array) {
        this.array = array;
    }

    static SwapArray<Integer> swapMethod(SwapArray<Integer> intArray, int element1, int element2) {
        int temp = intArray.array[element2];
        intArray.array[element2] = intArray.array[element1];
        intArray.array[element1] = temp;

        return intArray;
    }

    public static void main(String[] args) {

        SwapArray<Integer> intArray = new SwapArray<>(10, 1, 2, 4, 5, 1);
        System.out.println("Массив элеметов: " + Arrays.toString(intArray.array));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите индекс элемента, который хотите переместить: ");
        int element1 = scanner.nextInt();
        System.out.print("Введите индекс, куда хотите переместить: ");
        int element2 = scanner.nextInt();

        System.out.println("Измененный массив: " + Arrays.toString(swapMethod(intArray, element1, element2).array));
    }

}
