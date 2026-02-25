package org.firstinspires.ftc.teamcode.common.subsystems;

import dev.nextftc.core.subsystems.Subsystem;
import dev.nextftc.hardware.impl.MotorEx;

import org.firstinspires.ftc.teamcode.common.Parts;

public class Flywheel implements Subsystem {
    public static final Flywheel INSTANCE = new Flywheel();

    private Flywheel() {}
    public enum ShooterMode{
        OFF,
        ON,
        FAR
    }
    public static ShooterMode mode = ShooterMode.OFF;
    public void setPIDFVelocityCustom(MotorEx Motor, double velocityTarget, double kP, double kI, double kD) {
        // credits to metal masters for these equations
        double kPEquation = (kP * (velocityTarget - Motor.getVelocity()));
        double kIEquation = (kI * velocityTarget);
        double kDEquation = (kD * Math.signum(velocityTarget));

        double totalpower = kPEquation + kIEquation + kDEquation;

        Motor.setPower(totalpower);
    }

    @Override
    public void periodic() {
        switch (mode) {
            case OFF:
                Flywheel.INSTANCE.setPIDFVelocityCustom(Parts.flywheel, 0, 0, 0, 0);
                break;
            case ON:
                Flywheel.INSTANCE.setPIDFVelocityCustom(Parts.flywheel, 530, 0.015, 0.000004, 0.0006);
                break;
            case FAR:
                Flywheel.INSTANCE.setPIDFVelocityCustom(Parts.flywheel, 1130, 0.033, 0.00001, 0.0006);
                break;

        }
    }

}

