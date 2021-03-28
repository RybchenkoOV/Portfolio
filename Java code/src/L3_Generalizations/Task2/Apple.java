package L3_Generalizations.Task2;

public class Apple extends Fruit {
    private float weight;

    public Apple(float weight) {
        super(weight);
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                '}';
    }
}
