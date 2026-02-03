package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.extentions.AimAtPose;
import org.firstinspires.ftc.teamcode.extentions.LimeLight;

@TeleOp(name = "limeLight")
public class LimeLightTeleop extends CommandOpMode {
    private LimeLight LIMELIGHT;
    private AimAtPose AIM;

    private LLResult llResult;
    private int tagID;
    private double distance;

    private Limelight3A limelight;

    @Override
    public void initialize() {
        LIMELIGHT = new LimeLight(hardwareMap, telemetry);
        limelight = LIMELIGHT.limelight;

        AIM = new AimAtPose();
    }

    @Override
    public void run() {
        llResult = limelight.getLatestResult();
        if (llResult != null && llResult.isValid()) {
            if (!llResult.getFiducialResults().isEmpty()) {
                distance = LIMELIGHT.getDistanceFromTage(llResult.getTa());
                tagID = llResult.getFiducialResults().get(0).getFiducialId();

                telemetry.addData("tag ID", tagID);
                telemetry.addData("target Area", llResult.getTa());
                telemetry.addData("distance", distance);
                telemetry.addData("RPM Needed", AIM.getRPM(distance));
                telemetry.update();
            }
        }
    }
}
