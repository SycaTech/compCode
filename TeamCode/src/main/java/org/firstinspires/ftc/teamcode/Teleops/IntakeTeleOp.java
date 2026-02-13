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
public class IntakeTeleOp extends  CommandOpMode {

    private GamepadEx gamepadEx;
    private Intake intake;

    @Override
    public void initialize() {
        gamepadEx = new GamepadEx(gamepad1);
        intake = new Intake(telemetry, hardwareMap);

        gamepadEx.getGamepadButton(GamepadKeys.Button.A).whenPressed(intake.PowerIntake(1));
        gamepadEx.getGamepadButton(GamepadKeys.Button.Y).whenPressed(intake.PowerIntake(0));
        gamepadEx.getGamepadButton(GamepadKeys.Button.B).whenPressed(intake.PowerIntake(-1));

        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(intake.PowerIndex(0.5));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(intake.PowerIndex(0));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(intake.PowerIndex(-0.5));
    }

    @Override
    public void run(){
        super.run();
        telemetry.update();
    }
    }
