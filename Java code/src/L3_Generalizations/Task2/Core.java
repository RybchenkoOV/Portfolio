package L3_Generalizations.Task2;

import java.util.Random;

public class Core {
    static Random rnd = new Random();

    public static void main(String[] args) {

        GenBox<Apple> appleGenBox = new GenBox<>();
        GenBox<Orange> orangeGenBox = new GenBox();
        GenBox<Orange> emptyBox = new GenBox<>(); // Еще одну коробку создаем для перемещения

        Apple apple = new Apple(1.0f);
        Orange orange = new Orange(1.5f);

        for (int i = 0; i < rnd.nextInt(10) + 1; i++) {
            appleGenBox.addToFruitBox(apple);
        }
        System.out.println("Коробка с яблоками: " + appleGenBox);
        System.out.println("Ее вес: " + appleGenBox.getNetWeight());

        for (int i = 0; i < rnd.nextInt(10) + 1; i++) {
            orangeGenBox.addToFruitBox(orange);
        }
        System.out.println("Коробка с апельсинами: " + orangeGenBox);
        System.out.println("Ее вес: " + orangeGenBox.getNetWeight());

        System.out.println("Яблоки тяжелее апельсинов?: " + appleGenBox.checkWeight(orangeGenBox));

        System.out.println("Перемещаем аельсины в пустую коробку с апельсинами: ");
        System.out.println("Вот ее содержимое: " + orangeGenBox.swapBox(emptyBox));
//        System.out.println("Перемещаем аельсины в пустую коробку с апельсинами: " + orangeGenBox.swapBox(appleGenBox)); // А вот так ругается, потому что там яблоки

    }
}
