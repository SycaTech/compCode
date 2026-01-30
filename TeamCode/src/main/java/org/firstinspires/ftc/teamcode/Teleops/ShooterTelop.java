package org.firstinspires.ftc.teamcode.Teleops;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.bylazar.gamepad.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.DrivetrainBase;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp(name = "shooter")
public class ShooterTelop extends CommandOpMode {
    public GamepadEx shoot;
    public Shooter shooterr;

    @Override
    public void initialize() {
        GamepadEx gamepadEx = new GamepadEx(gamepad1);
        shooterr = new Shooter(hardwareMap);
        gamepadEx.getGamepadButton(GamepadKeys.Button.A).whenPressed()
    }

        GamepadButton sh = new GamepadButton(gamepad1, GamepadKeys.Button.A);


}
}
