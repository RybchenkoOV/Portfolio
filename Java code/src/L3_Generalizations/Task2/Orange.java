package L3_Generalizations.Task2;

public class Orange extends Fruit {
    private float weight;

    public Orange(float weight) {
        super(weight);
        this.weight = weight;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                '}';
    }
}
