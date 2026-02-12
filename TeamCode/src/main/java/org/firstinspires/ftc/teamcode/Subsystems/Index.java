package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Index extends SubsystemBase {
    private Motor indexer;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private PIDController pid;

    private double output;

    public Index(HardwareMap hMap, Telemetry telemetry) {
        pid = new PIDController(0.05, 0.0, 0.001);

        this.hardwareMap = hMap;
        this.telemetry = telemetry;

        indexer = new Motor(this.hardwareMap, "indexer");

        indexer.stopAndResetEncoder();

        indexer.setRunMode(Motor.RunMode.PositionControl);

        indexer.set(0);
    }

    public void goToTarget(double ticks) {
        pid.setSetPoint(ticks);
        while (!pid.atSetPoint()) {
            output = pid.calculate(
                    indexer.getCurrentPosition(), pid.getSetPoint()
            );
            indexer.set(output);
        }
        indexer.stopMotor();
    }

    public Command Ball1() {
        return new InstantCommand(() -> goToTarget(120));
    }

    public Command Ball2() {
        return new InstantCommand(() -> goToTarget(240));
    }

    public Command Ball3() {
        return new InstantCommand(() -> goToTarget(360));
    }

    @Override
    public void periodic() {
        indexer.set(output);
        telemetry.addData("Position", indexer.getCurrentPosition());
        telemetry.addData("Target", pid.getSetPoint());
    }
}
