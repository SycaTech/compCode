package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "MecanumWheelsTest")
public class MecanumWheelsTestTeleop extends CommandOpMode {
    private MecanumWheelsTest mecanumWheelsTest;
    private GamepadEx operator;

    @Override
    public void initialize() {
        mecanumWheelsTest = new MecanumWheelsTest(hardwareMap, telemetry);
        operator = new GamepadEx(gamepad1);
        operator.getGamepadButton(GamepadKeys.Button.B).whenActive(mecanumWheelsTest.power());
    }

    @Override
    public void run() {

    }
}
