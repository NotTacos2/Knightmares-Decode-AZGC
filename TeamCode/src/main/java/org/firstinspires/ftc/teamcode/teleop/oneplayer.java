package org.firstinspires.ftc.teamcode.teleop;

import dev.nextftc.bindings.BindingManager;
import dev.nextftc.core.commands.Command;
import dev.nextftc.ftc.NextFTCOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import dev.nextftc.hardware.driving.FieldCentric;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.ftc.components.BulkReadComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.hardware.driving.MecanumDriverControlled;

import org.firstinspires.ftc.teamcode.common.subsystems.Gate;
import org.firstinspires.ftc.teamcode.common.subsystems.Flywheel;
import org.firstinspires.ftc.teamcode.common.subsystems.Intake;
import org.firstinspires.ftc.teamcode.common.subsystems.Outtake;
import org.firstinspires.ftc.teamcode.common.Parts;

@TeleOp(group = "Decode")
public class oneplayer extends NextFTCOpMode {
    Parts part = new Parts();
    public oneplayer() {
        addComponents(
                new SubsystemComponent(Gate.INSTANCE, Flywheel.INSTANCE, Intake.INSTANCE, Outtake.INSTANCE),
                BulkReadComponent.INSTANCE,
                BindingsComponent.INSTANCE
        );
    }

    @Override
    public void onStartButtonPressed() {
        Command driverControlled = new MecanumDriverControlled(
                Parts.FL,
                Parts.FR,
                Parts.BL,
                Parts.BR,
                Gamepads.gamepad1().leftStickY().negate(),
                Gamepads.gamepad1().leftStickX(),
                Gamepads.gamepad1().rightStickX(),
                new FieldCentric(Parts.imu)
        );
        driverControlled.schedule();

        Gamepads.gamepad1().rightTrigger().greaterThan(0.2).whenBecomesTrue(
                Gate.INSTANCE.turn
        ).whenBecomesFalse(
                Gate.INSTANCE.reset
        );

        Gamepads.gamepad1().leftBumper()
                .whenBecomesTrue(() -> Flywheel.mode = Flywheel.ShooterMode.ON)
                .whenBecomesFalse(() -> Flywheel.mode = Flywheel.ShooterMode.OFF);

        Gamepads.gamepad1().rightBumper()
                .whenBecomesTrue(() -> Flywheel.mode = Flywheel.ShooterMode.FAR)
                .whenBecomesFalse(() -> Flywheel.mode = Flywheel.ShooterMode.OFF);

        Gamepads.gamepad1().leftTrigger().greaterThan(0.2)
                .whenBecomesTrue(Outtake.INSTANCE.forward)
                .whenBecomesTrue(Intake.INSTANCE.forward)
                .whenBecomesFalse(Outtake.INSTANCE.stop)
                .whenBecomesFalse(Intake.INSTANCE.stop);

        Gamepads.gamepad1().b()
                .whenBecomesTrue(Outtake.INSTANCE.reverse)
                .whenBecomesTrue(Intake.INSTANCE.reverse)
                .whenBecomesFalse(Outtake.INSTANCE.stop)
                .whenBecomesFalse(Intake.INSTANCE.stop);


    }

    @Override
    public void onUpdate() {
        BindingManager.update();

        telemetry.addData("Velocity: ", Parts.flywheel.getVelocity());
        telemetry.update();
    }

    @Override
    public void onStop() {
        BindingManager.reset();
    }
}