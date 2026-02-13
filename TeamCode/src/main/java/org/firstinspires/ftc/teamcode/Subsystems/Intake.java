package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class Intake extends SubsystemBase {
    private Telemetry telemetry;

    private Motor intake;
    private Motor index;

    public Intake(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        intake = new Motor(hwMap, "intake");
        index = new Motor(hwMap, "index");
    }
    final double TICKS_PER_REVOLUTION = 103.6;

    @Override
    public void periodic() {
        double velocityTPSL = intake.getRate();
        double velocityRPML = (velocityTPSL / TICKS_PER_REVOLUTION) * 60.0;

        telemetry.addData("power intake", intake.get());
        telemetry.addData("power index", index.get());

    }

    public void setIntake(double power) {
        intake.set(power);
    }

    public void setIndex(double power) {
        index.set(power);
    }

    public Command PowerIntake(double VOLT) {
        return new InstantCommand(() -> setIntake(VOLT));
    }

    public Command PowerIndex(double VOLT) {
        return new InstantCommand(() -> setIndex(VOLT));
    }
}