package org.firstinspires.ftc.teamcode.common.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.positionable.SetPosition;
import org.firstinspires.ftc.teamcode.common.Parts;

public class Gate implements Subsystem {
    public static final Gate INSTANCE = new Gate();
    private Gate() {}

    public Command turn = new SetPosition(Parts.gate, 1).requires(this);
    public Command reset = new SetPosition(Parts.gate, -1).requires(this);
}