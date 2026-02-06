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
    private int tagID;
    private double distance;
    public double RPM_Needed;


    @Override
    public void initialize() {
        aim = new AimAtPose(hardwareMap, telemetry);
    }

    @Override
    public void run() {
        super.run();

        telemetry.update();
    }
}
