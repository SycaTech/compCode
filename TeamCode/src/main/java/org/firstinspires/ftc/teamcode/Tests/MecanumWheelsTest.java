package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumWheelsTest extends SubsystemBase {
    public Motor frontLeft;
    public Motor frontRight;
    public Motor backLeft;
    public Motor backRight;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public MecanumWheelsTest(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hardwareMap = hwMap;

        frontLeft = new Motor(hardwareMap, "FL");
        frontRight = new Motor(hardwareMap, "FR");
        backLeft = new Motor(hardwareMap, "BL");
        backRight = new Motor(hardwareMap, "BR");

        frontLeft.setRunMode(Motor.RunMode.RawPower);
        frontRight.setRunMode(Motor.RunMode.RawPower);
        backLeft.setRunMode(Motor.RunMode.RawPower);
        backRight.setRunMode(Motor.RunMode.RawPower);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void frontLeft(double power) {
        frontLeft.set(power);
    }

    public void frontRight(double power) {
        frontRight.set(power);
    }

    public void backLeft(double power) {
        backLeft.set(power);
    }

    public void backRight(double power) {
        backRight.set(power);
    }

    public Command powerFL(double power) {
        return new InstantCommand(() -> frontLeft(power));
    }

    public Command powerFR(double power) {
        return new InstantCommand(() -> frontRight(power));
    }

    public Command powerBL(double power) {
        return new InstantCommand(() -> backLeft(power));
    }
    public Command powerBR(double power) {
        return new InstantCommand(() -> backRight(power));
    }

}
