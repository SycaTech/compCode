package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class LimeLight extends OpMode {
    public Limelight3A limelight;
    private double distance;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); //Scanning April tags
    }

    @Override
    public void start() {
        limelight.start();
    }

    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            if (!llResult.getFiducialResults().isEmpty()) {

                int TagID = llResult.getFiducialResults().get(0).getFiducialId();
                distance = getDistanceFromTage(llResult.getTa());

                telemetry.addData("distance", distance);
                telemetry.addData("tag ID", TagID);
                telemetry.update();
            }
        }
    }

    private static final double K = 1.748501249;

    public double getDistanceFromTage(double ta) {
        ta = Math.max(ta, 0.01);
        return (K / Math.sqrt(ta)) * 100 + 3;
    }
}
