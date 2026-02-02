package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.extentions.AimAtPose;

@TeleOp(name = "aimAtPose")
public class AimAtPoseTeleop extends CommandOpMode {
    private AimAtPose aim;

    private double distance = 0.3;

    @Override
    public void initialize() {
        aim = new AimAtPose();
        if (gamepad1.dpad_up) {
            distance += 0.1;
        }else if (gamepad1.dpad_down) {
            distance -= 0.1;
        }
        telemetry.addData("distance", distance);
        telemetry.update();
    }

    @Override
    public void run() {
        telemetry.addData("RPM", aim.getRPM(distance));
        telemetry.update();
    }
}
