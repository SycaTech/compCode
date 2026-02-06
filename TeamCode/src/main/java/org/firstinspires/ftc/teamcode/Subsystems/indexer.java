package org.firstinspires.ftc.teamcode.Subsystems;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class indexer extends SubsystemBase {
    private Motor motor;

    public indexer(HardwareMap hMap) {
        motor = new Motor(hMap, "indexer");

        motor.stopAndResetEncoder();

        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        motor.setRunMode(Motor.RunMode.PositionControl);

        motor.setPositionCoefficient(0.005);
    }

    public void setTarget(int ticks) {
        motor.setTargetPosition(ticks);
        motor.set(0.5);
    }

    public Command setTAR(int ticks) {
        return new InstantCommand(() -> setTarget(ticks));
    }
    public int getCurrentPos() {
        return motor.getCurrentPosition();
    }
}