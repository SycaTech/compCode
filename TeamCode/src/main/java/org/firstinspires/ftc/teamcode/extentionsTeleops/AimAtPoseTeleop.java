package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.extentions.AimAtPose;
import org.firstinspires.ftc.teamcode.extentions.LimeLight;

@TeleOp(name = "aimAtPose")
public class AimAtPoseTeleop extends CommandOpMode {
    private AimAtPose aim;
    private LimeLight LIMELIGHT;
    private Limelight3A limelight;
    private LLResult limelightResult;
    private int tagID;
    private double distance;
    public double RPM_Needed;


    @Override
    public void initialize() {
        aim = new AimAtPose();
        LIMELIGHT = new LimeLight(hardwareMap, telemetry);
        limelight = LIMELIGHT.limelight;
    }

    @Override
    public void run() {
        super.run();
        limelightResult = limelight.getLatestResult();
        if (limelightResult != null &&limelightResult.isValid()) {
            distance = LIMELIGHT.getDistanceFromTage(limelightResult.getTa());
            tagID = limelightResult.getFiducialResults().get(0).getFiducialId();
            RPM_Needed = aim.getRPM(distance);
            telemetry.addData("Distance", distance);
            telemetry.addData("RPM needed", RPM_Needed);
            telemetry.addData("Tag ID", tagID);
        } else {
            telemetry.addLine("No april tag found");
        }
        telemetry.update();
    }
}
