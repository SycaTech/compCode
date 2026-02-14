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

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.extentions.AimAtPose;
import org.firstinspires.ftc.teamcode.extentions.LimeLight;

@TeleOp(name = "aimAtPose")
public class AimAtPoseTeleop extends CommandOpMode {
    private AimAtPose aim;
    private Shooter shooter;

    public double RPM_Needed;

    private GamepadEx operator;


    @Override
    public void initialize() {
        aim = new AimAtPose(hardwareMap, telemetry);
        shooter = new Shooter(hardwareMap, telemetry);
        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(shooter.power(aim.neededRPM));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(shooter.power(0));
    }
}
