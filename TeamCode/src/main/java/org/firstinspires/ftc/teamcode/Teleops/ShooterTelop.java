package org.firstinspires.ftc.teamcode.Teleops;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp(name = "shooter")
public class ShooterTelop extends CommandOpMode {
    private GamepadEx shoot;
    public Shooter shooter;

    @Override
    public void initialize() {
        shoot = new GamepadEx(gamepad1);
        shooter = new Shooter(hardwareMap , telemetry);
        shoot.getGamepadButton(GamepadKeys.Button.A).whenPressed(shooter.setShootRPM(1400));
        shoot.getGamepadButton(GamepadKeys.Button.B).whenPressed(shooter.setShootRPM(0));
    }
    @Override
    public void run(){
        super.run();
        telemetry.update();
    }
}

