package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class LimeLight extends SubsystemBase {
    public Limelight3A limelight;
    private static double distance;

    private int tagID;
    public LLResult llResult;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public LimeLight(HardwareMap hwMap, Telemetry telemetry) {
        this.hardwareMap = hwMap;
        this.telemetry = telemetry;
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); //Scanning April tags
        limelight.start();
    }

    private static final double K = 1.756760881;

    public double getDistanceFromTage(double ta) {
        ta = Math.max(ta, 0.01);
        return (K / Math.sqrt(ta)) * 100 + 3;
    }
}
