package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainBase;

@TeleOp(name = "drive")
public class DrivetrainTeleop extends OpMode {
    private GamepadEx driverController;
    private DrivetrainBase drive;

    @Override
    public void init() {
        drive = new DrivetrainBase(hardwareMap);
        driverController = new GamepadEx(gamepad1);
    }

    @Override
    public void loop() {
        FieldCentricMecanum();
        drive.loop(telemetry);
    }

    public Command FieldCentricMecanum() {
        return new InstantCommand(() -> drive.mecanum.driveFieldCentric(
                driverController.getLeftX(),
                driverController.getLeftY(),
                driverController.getRightX(),
                drive.imu.getRotation2d().getDegrees()
        ));
    }

    public Command RobotCentricMecanum() {
        return new InstantCommand(() -> drive.mecanum.driveRobotCentric(
                driverController.getLeftX(),
                driverController.getLeftY(),
                driverController.getRightX()
        ));
    }
}
