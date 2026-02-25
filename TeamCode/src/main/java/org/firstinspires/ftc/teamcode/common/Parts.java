package org.firstinspires.ftc.teamcode.common;

import dev.nextftc.hardware.impl.Direction;
import dev.nextftc.hardware.impl.MotorEx;
import dev.nextftc.hardware.impl.ServoEx;
import dev.nextftc.hardware.impl.IMUEx;

public class Parts {

    // declaring parts
    public static MotorEx FR, FL, BR, BL;
    public static MotorEx flywheel;
    public static MotorEx intake;
    public static MotorEx outtake;
    public static ServoEx gate;
    public static IMUEx imu;

    public Parts() {
        FL = new MotorEx("lf").reversed().brakeMode();
        FR = new MotorEx("rf").brakeMode();
        BR = new MotorEx("rr").brakeMode();
        BL = new MotorEx("lr").reversed().brakeMode();

        flywheel = new MotorEx("shooterOne").floatMode();
        intake = new MotorEx("intake").brakeMode();
        outtake = new MotorEx("outtake").brakeMode();

        gate = new ServoEx("gate");

        imu = new IMUEx("imu", Direction.UP, Direction.RIGHT).zeroed();
    }
}