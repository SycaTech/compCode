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

    private GamepadEx conroller;
    private Intake intake;

    @Override
    public void initialize() {
        conroller = new GamepadEx(gamepad1);
        intake = new Intake(hardwareMap, telemetry);

        conroller.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileActiveContinuous(intake.Power(-0.7));
        conroller.getGamepadButton(GamepadKeys.Button.DPAD_UP).whileActiveContinuous(intake.Power(0.7));
        conroller.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whileActiveContinuous(intake.Power(0));
    }

    @Override
    public void run(){
        super.run();
        telemetry.update();
        }
    }
