package org.firstinspires.ftc.teamcode.Teleops;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.indexer;

@TeleOp(name="Final PID TeleOp")
public class IndexerTeleop extends CommandOpMode {

    private indexer arm;
    private GamepadEx drive;

    @Override
    public void initialize() {
        arm = new indexer(hardwareMap);
        drive = new GamepadEx(gamepad1);

        drive.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> arm.setTAR(0));

        drive.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> arm.setTAR(32));

        drive.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(() -> arm.setTAR(64));
    }

    @Override
    public void run() {
        super.run();
        telemetry.addData("Arm Position", arm.getCurrentPos());
        telemetry.update();
    }
}