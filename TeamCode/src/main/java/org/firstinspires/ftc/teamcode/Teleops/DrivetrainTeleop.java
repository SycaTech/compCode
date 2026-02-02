package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Subsystem;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainBase;

import java.util.function.DoubleSupplier;

@TeleOp(name = "drive")
public class DrivetrainTeleop extends CommandOpMode {
    private GamepadEx driverController;
    private DrivetrainBase drive;


    @Override
    public void initialize() {
        driverController = new GamepadEx(gamepad1);
        drive = new DrivetrainBase(hardwareMap, telemetry);
<<<<<<< HEAD
=======

        drive.setDefaultCommand(
                drive.driveFieldCentric(
                    () -> driverController.getLeftY(),
                    () -> driverController.getLeftX(),
                    () -> driverController.getRightX())
        );
>>>>>>> 19ebd57519ca7904508dbac8c3faf1cd0c069503
    }

    @Override
    public void run(){

    }
}
