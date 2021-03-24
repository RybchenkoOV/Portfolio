package L1_Objects.Obstacles;

import L1_Objects.Basic_Abilities.CanJump;
import L1_Objects.Basic_Abilities.CanRun;
import L1_Objects.Obstacles.Obstacles;

public class Wall implements Obstacles {
private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean checkJump(CanJump jumper) {
        if (jumper.jump()>=height) return true;
    return false;
    }

    @Override
    public boolean checkRun(CanRun runner) {
        return true;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getLength() {
        return 0;
    }
}
