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
    public Telemetry telemetry;

    public DcMotorEx intake;

    public Intake(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        intake = hwMap.get(DcMotorEx.class, "intake");

        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    final double TICKS_PER_REVOLUTION = 103.6;

    @Override
    public void periodic() {
        double velocityTPSL = intake.getVelocity();
        double velocityRPML = (velocityTPSL / TICKS_PER_REVOLUTION) * 60.0;

        telemetry.addData("power", intake.getPower());

    }

    public void setPower(double power) {
        intake.setPower(power);

    }

    public Command setpower(double VOLT) {
        return new InstantCommand(() -> setPower(VOLT));
    }
}