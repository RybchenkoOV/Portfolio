package L1_Objects.Obstacles;

import L1_Objects.Basic_Abilities.CanJump;
import L1_Objects.Basic_Abilities.CanRun;

public interface Obstacles {
    boolean checkJump(CanJump jumper);
    boolean checkRun(CanRun runner);
    int getHeight();
    int getLength();

}
