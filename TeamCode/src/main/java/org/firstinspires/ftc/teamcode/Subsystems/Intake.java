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
    private DcMotorEx intake;

    public Intake(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        intake = hwMap.get(DcMotorEx.class, "intake");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intake.setVelocity(1);

    }
    final double TICKS_PER_REVOLUTION = 103.6;

    @Override
    public void periodic() {
        double velocityTPS = intake.getVelocity();
        double velocityRPM = (velocityTPS / TICKS_PER_REVOLUTION) * 60.0;

        telemetry.addData("rpm", velocityRPM);

    }

    public Command Power(double VOLT) {
        return new InstantCommand(() -> intake.setPower(VOLT));
    }
}