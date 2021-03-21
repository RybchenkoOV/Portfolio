package L1_Objects.Participants;

public class Human implements Party {
    private String name;
    private int jumpAbility;
    private int runAbility;

    public Human(String name, int jumpAbility, int runAbility) {
        this.name = name;
        this.jumpAbility = jumpAbility;
        this.runAbility = runAbility;
    }

    @Override
    public int jump() {
        return jumpAbility;
    }

    @Override
    public int run() {
        return runAbility;
    }

    @Override
    public String whoIs() {
        return name;
    }
}
