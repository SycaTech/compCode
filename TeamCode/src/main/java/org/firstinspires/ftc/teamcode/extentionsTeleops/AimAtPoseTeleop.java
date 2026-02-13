package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.extentions.AimAtPose;
import org.firstinspires.ftc.teamcode.extentions.LimeLight;

@TeleOp(name = "aimAtPose")
public class AimAtPoseTeleop extends CommandOpMode {
    private AimAtPose aim;

    private LimeLight LIMELIGHT;
    private LLResult llResult;

    private int tagID;
    private double distance;
    public double RPM_Needed;

    private GamepadEx operator;


    @Override
    public void initialize() {
        aim = new AimAtPose(hardwareMap, telemetry);
        operator = new GamepadEx(gamepad1);
        LIMELIGHT = new LimeLight(hardwareMap, telemetry);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(aimAtPose());
    }

    @Override
    public void run() {
        super.run();
        llResult = LIMELIGHT.limelight.getLatestResult();
        distance = LIMELIGHT.getDistanceFromTage(llResult.getTa());
        telemetry.addData("Distance", distance);
        telemetry.addData("RPM needed", RPM_Needed);
        telemetry.update();
    }

    public void calculateRPM() {
        distance = LIMELIGHT.getDistanceFromTage(llResult.getTa());
        RPM_Needed = aim.getRPM(distance);
    }

    public Command aimAtPose() {
        return new InstantCommand(
                this::calculateRPM
        );
    }
}
