package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.shoot;

@TeleOp(name = "shootTeleop")
public class shootTeleop extends CommandOpMode {

    private shoot shooter;
    private GamepadEx controller;

    @Override
    public void initialize() {
        controller = new GamepadEx(gamepad1);
        shooter = new shoot(hardwareMap, telemetry);

        controller.getGamepadButton(GamepadKeys.Button.A).whenPressed(shooter.setShootRPM(1400));
        controller.getGamepadButton(GamepadKeys.Button.B).whenPressed(shooter.setShootRPM(0));
    }

    @Override
    public void run(){
        super.run();
        telemetry.update();
    }
}