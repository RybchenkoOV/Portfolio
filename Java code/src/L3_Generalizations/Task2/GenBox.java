package L3_Generalizations.Task2;

import java.util.ArrayList;

public class GenBox<T extends Fruit> {
    private ArrayList<T> fruitArray = new ArrayList<>();

    @Override
    public String toString() {
        return "GenBox{" +
                "fruitArray=" + fruitArray +
                '}';
    }

    void addToFruitBox(T fruit) {
        fruitArray.add(fruit);
    }

    float getNetWeight() {
        float netWeight = 0;
        for (int i = 0; i < fruitArray.size(); i++) {
            netWeight += (fruitArray.get(i).getWeight());
        }
        return netWeight;
    }

    boolean checkWeight(GenBox<?> fruitBox) {
        return this.getNetWeight() == fruitBox.getNetWeight();
    }

    public GenBox<T> swapBox(GenBox<T> fruitBox) {
        for (int i = 0; i < fruitArray.size(); i++) {
            fruitBox.addToFruitBox(fruitArray.get(i));
        }
        fruitArray.clear();
        return fruitBox;
    }

}

