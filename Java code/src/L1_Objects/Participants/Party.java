package L1_Objects.Participants;

import L1_Objects.Basic_Abilities.CanJump;
import L1_Objects.Basic_Abilities.CanRun;

public interface Party extends CanJump, CanRun {
    String whoIs();
}
