package org.firstinspires.ftc.teamcode.common.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.powerable.SetPower;
import org.firstinspires.ftc.teamcode.common.Parts;

public class Outtake implements Subsystem {
    public static final Outtake INSTANCE = new Outtake();
    private Outtake() {}


    public Command forward = new SetPower(Parts.outtake, 0.6).requires(this);
    public Command reverse = new SetPower(Parts.outtake, -0.6).requires(this);
    public Command stop = new SetPower(Parts.outtake, 0).requires(this);
}