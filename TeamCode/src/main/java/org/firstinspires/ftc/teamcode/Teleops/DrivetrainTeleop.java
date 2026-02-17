package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainBase;

@TeleOp(name = "drive")
public class
DrivetrainTeleop extends CommandOpMode {
    private GamepadEx driverController;
    private DrivetrainBase drive;


    @Override
    public void initialize() {
        driverController = new GamepadEx(gamepad1);
        drive = new DrivetrainBase(hardwareMap, telemetry);

        drive.setDefaultCommand(
                drive.FieldCentricMecanum(
                    () -> driverController.getLeftX(),
                    () -> driverController.getLeftY(),
                    () -> driverController.getRightX())
        );
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
