package org.firstinspires.ftc.teamcode.Teleops;


import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp(name = "intake")
public class IntakeTeleOp extends  CommandOpMode {

    private GamepadEx gamepadEx;
    private Intake intake;
    private Shooter shooter;

    @Override
    public void initialize() {
        gamepadEx = new GamepadEx(gamepad1);
        intake = new Intake(telemetry, hardwareMap);
        shooter = new Shooter(hardwareMap, telemetry);


        gamepadEx.getGamepadButton(GamepadKeys.Button.A).whenPressed(intake.Power(0.8));

        gamepadEx.getGamepadButton(GamepadKeys.Button.Y).whenPressed(intake.Power(0));

        gamepadEx.getGamepadButton(GamepadKeys.Button.B).whenPressed(intake.Power(-0.8));

        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(shooter.power(2300));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(shooter.power(0));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenPressed(intake.fPower(1.0));
        gamepadEx.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(intake.fPower(-1));
        gamepadEx.getGamepadButton(GamepadKeys.Button.X).whenPressed(intake.fPower(0));

    }

    @Override
    public void run(){
        super.run();
        telemetry.update();
    }
    }
