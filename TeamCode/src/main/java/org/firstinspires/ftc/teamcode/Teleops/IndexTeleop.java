package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Index;

@TeleOp(name = "index")
public class IndexTeleop extends CommandOpMode {
    private Index indexer;
    private GamepadEx operator;


    @Override
    public void initialize() {
        indexer = new Index(hardwareMap, telemetry);
        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(indexer.Ball1());
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(indexer.Ball2());
        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(indexer.Ball3());
    }

    @Override
    public void run() {
        super.run();
    }


}
