package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "limeLight test")
public class LimeLight extends CommandOpMode {
    private Limelight3A limelight;
    private double distance;
    private int tagID;
    private LLResult llResult;

    @Override
    public void initialize() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); //Scanning April tags
        limelight.start();
        llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            if (!llResult.getFiducialResults().isEmpty()) {

                tagID = llResult.getFiducialResults().get(0).getFiducialId();

                telemetry.addData("tag ID", tagID);
                telemetry.update();
            }
        }
    }


    @Override
    public void run() {
        llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            if (!llResult.getFiducialResults().isEmpty()) {
                distance = getDistanceFromTage(llResult.getTa());

                telemetry.addData("tag ID", tagID);
                telemetry.addData("distance", distance);
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
