package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumWheelsTest extends SubsystemBase {
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor backLeft;
    private final Motor backRight;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    public MecanumWheelsTest(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.hardwareMap = hwMap;

        frontLeft = hardwareMap.get(Motor.class, "FL");
        frontRight = hardwareMap.get(Motor.class, "FR");
        backLeft = hardwareMap.get(Motor.class, "BL");
        backRight = hardwareMap.get(Motor.class, "BR");

        frontLeft.setInverted(true);
        backLeft.setInverted(true);

        frontLeft.setRunMode(Motor.RunMode.RawPower);
        frontRight.setRunMode(Motor.RunMode.RawPower);
        backLeft.setRunMode(Motor.RunMode.RawPower);
        backRight.setRunMode(Motor.RunMode.RawPower);
    }

    public void Power(double power) {
        frontLeft.set(power);
        frontRight.set(power);
        backLeft.set(power);
        backRight.set(power);
    }

    public Command power() {
        return new RunCommand(() -> Power(1.0), this);
    }
}
