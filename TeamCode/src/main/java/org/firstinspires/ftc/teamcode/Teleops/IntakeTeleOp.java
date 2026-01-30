package org.firstinspires.ftc.teamcode.Teleops;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;


@TeleOp(name = "intake")
public class IntakeTeleOp extends CommandOpMode {

    private Intake intakeSubsystem = new Intake();
    private GamepadEx gamepadEx;
    private Motor motor;

    @Override
    public void initialize() {
        gamepadEx = new GamepadEx(gamepad1);
    }

    Button intakeGO = new GamepadButton(
            gamepadEx, GamepadKeys.Button.Y
    );
    GamepadButton grabButton = new GamepadButton(
            gamepadEx, GamepadKeys.Button.A
    );

    GamepadButton Outtake = new GamepadButton(
            gamepadEx, GamepadKeys.Button.B
    );

}