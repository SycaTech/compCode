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

        operator.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(() -> mecanumWheelsTest.frontLeft(1.0))
                .whenReleased(() -> mecanumWheelsTest.frontLeft(0.0));

        operator.getGamepadButton(GamepadKeys.Button.B)
                .whenPressed(() -> mecanumWheelsTest.frontRight(1.0))
                .whenReleased(() -> mecanumWheelsTest.frontRight(0.0));

        operator.getGamepadButton(GamepadKeys.Button.X)
                .whenPressed(() -> mecanumWheelsTest.backLeft(1.0))
                .whenReleased(() -> mecanumWheelsTest.backLeft(0.0));

        operator.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> mecanumWheelsTest.backRight(1.0))
                .whenReleased(() -> mecanumWheelsTest.backRight(0.0));
    }
}
