package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Index;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.extentions.ColorSensor;

@TeleOp(name = "index")
public class IndexTeleop extends CommandOpMode {
    private Index indexer;
    private Intake intake;
    private Shooter shooter;
    private GamepadEx operator;
    private ColorSensor colorSensor;

    @Override
    public void initialize() {
        indexer = new Index(hardwareMap, telemetry);
        intake = new Intake(telemetry, hardwareMap);
        shooter = new Shooter(hardwareMap, telemetry);
        colorSensor = new ColorSensor(telemetry, hardwareMap);
        operator = new GamepadEx(gamepad1);

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP).whenPressed(intake.Power(1.0));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenPressed(intake.Power(0.0));
        operator.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenPressed(intake.Power(-1.0));

        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(indexer.IndexChange());

        operator.getGamepadButton(GamepadKeys.Button.Y).whenPressed(indexer.goToTar());

        operator.getGamepadButton(GamepadKeys.Button.X).whenPressed(shooter.power(2300));
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(shooter.power(0));
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }



}
