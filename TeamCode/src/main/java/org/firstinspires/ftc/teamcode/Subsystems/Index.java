package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Index extends SubsystemBase {
    private Motor indexer;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private PIDController pController;

    private double output;
    private double target = 0;

    public Index(HardwareMap hMap, Telemetry telemetry) {
        pController = new PIDController(0.005, 0.0, 0.0001);

        this.hardwareMap = hMap;
        this.telemetry = telemetry;

        indexer = new Motor(this.hardwareMap, "index");

    }

    public void goToTarget(double ticks) {
        target = ticks;
        pController.setSetPoint(target);
        while (!pController.atSetPoint()) {
            output = pController.calculate(
                    indexer.getCurrentPosition()
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
        telemetry.addData("Position", indexer.getCurrentPosition());
        telemetry.addData("Target", pController.getSetPoint());
        telemetry.update();
    }
}
