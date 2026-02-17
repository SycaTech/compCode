package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.ColorSensor;

@Configurable
public class Index extends SubsystemBase {
    private ColorSensor colorSensor;
    private DcMotorEx indexer;
    private Telemetry telemetry;

    private PIDController pController;

    private int poseIndex = 0;

    private double[] poses = {
            0, 124, 249
    };

    public static double kP = 0.016;
    public static double kI = 0.0;
    public static double kD = 0.00001;


    public Index(HardwareMap hMap, Telemetry telemetry) {
        colorSensor = new ColorSensor(telemetry, hMap);
        indexer = hMap.get(DcMotorEx.class, "indexer");
        indexer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.telemetry = telemetry;
        pController = new PIDController(kP, kI, kD);
        goToTarget(poses[0]);
    }

    public void goToTarget (double ticks) {
        pController.setSetPoint(ticks);
    }

    public Command goToTar() {
        return new InstantCommand(() -> goToTarget(poses[poseIndex]));
    }

    public void changeIndex() {
        poseIndex++;
        if (poseIndex >= poses.length) poseIndex = 0;
    }

    public Command IndexChange() {
        return new InstantCommand(this::changeIndex);
    }

    public void CheckForBallAndIndex() {
        if (colorSensor.getDetectedColor() == ColorSensor.DETECTED_COLOR.BALL_IN) {
            goToTarget(poses[poseIndex]);
        }
    }


    @Override
    public void periodic() {
        CheckForBallAndIndex();
        double output = pController.calculate(indexer.getCurrentPosition());
        indexer.setPower(output);
        telemetry.addData("Position", indexer.getCurrentPosition());
        telemetry.addData("Target", pController.getSetPoint());
        telemetry.addData("Pose Index", poses[poseIndex]);
    }
}
