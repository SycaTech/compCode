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
        driverController = new GamepadEx(gamepad1);
        drive = new DrivetrainBase(hardwareMap, telemetry, driverController);
    }

    @Override
    public void loop() {
        CommandScheduler.getInstance().setDefaultCommand(drive, drive.FieldCentricMecanum());
        telemetry.update();
    }
}
