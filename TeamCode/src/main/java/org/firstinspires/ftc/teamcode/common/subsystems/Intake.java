package org.firstinspires.ftc.teamcode.common.subsystems;

import dev.nextftc.core.commands.Command;
import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.powerable.SetPower;
import org.firstinspires.ftc.teamcode.common.Parts;

public class Intake implements Subsystem {
    public static final Intake INSTANCE = new Intake();
    private Intake() {}


    public Command forward = new SetPower(Parts.intake, 1).requires(this);
    public Command reverse = new SetPower(Parts.intake, -1).requires(this);
    public Command stop = new SetPower(Parts.intake, 0).requires(this);
}