package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

public class Intake extends SubsystemBase {
    private Telemetry telemetry;
    private HardwareMap hardwareMap;

    public Intake(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hwMap;
        intake = hwMap.get(Motor.class, "intake");
    }

    public Motor intake;

    final double TICKS_PER_REVOLUTION = 103.6;

    @Override
    public void periodic() {
        double velocityTPSL = intake.getRate();
        double velocityRPML = (velocityTPSL / TICKS_PER_REVOLUTION) * 60.0;

        telemetry.addData("power", intake.get());
        telemetry.update();

    }

    public void setPower(double power) {
        intake.set(power);
    }

    public Command Power(double VOLT) {
        return new InstantCommand(() -> setPower(VOLT));
    }
}