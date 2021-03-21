package L1_Objects.Obstacles;

import L1_Objects.Basic_Abilities.CanJump;
import L1_Objects.Basic_Abilities.CanRun;
import L1_Objects.Obstacles.Obstacles;

public class Track implements Obstacles {
    private int length;

    public Track(int length) {
        this.length = length;
    }

    @Override
    public boolean checkJump(CanJump jumper) {
        return true;
    }

    @Override
    public boolean checkRun(CanRun runner) {
        if (runner.run() >= length) return true;
        return false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getLength() {
        return length;
    }
}
