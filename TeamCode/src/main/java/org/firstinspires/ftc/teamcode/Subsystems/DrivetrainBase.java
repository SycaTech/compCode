package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.Constants;

import java.util.function.DoubleSupplier;

public class DrivetrainBase extends SubsystemBase {
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor backLeft;
    private final Motor backRight;

    public Rev9AxisImu imu;
    public PIDController pid = new PIDController(Constants.Mecanum.kP, Constants.Mecanum.kI, Constants.Mecanum.kD);

    public MecanumDrive mecanum;
    Telemetry telemetry;

    public DrivetrainBase(HardwareMap hwMap, Telemetry telemetry, GamepadEx driverController) {
        frontLeft = new Motor(hwMap, Constants.Mecanum.frontLeftName, Motor.GoBILDA.RPM_435);
        frontRight = new Motor (hwMap, Constants.Mecanum.frontRightName, Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hwMap, Constants.Mecanum.backLeftName, Motor.GoBILDA.RPM_435);
        backRight = new Motor(hwMap, Constants.Mecanum.backRightName, Motor.GoBILDA.RPM_435);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.setInverted(true);
        backLeft.setInverted(true);

        imu = hwMap.get(Rev9AxisImu.class, Constants.Mecanum.imuName);
        imu.resetYaw();

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
        this.telemetry = telemetry;
    }

    public Command FieldCentricMecanum(DoubleSupplier x, DoubleSupplier y, DoubleSupplier rX) {
        return new RunCommand(
                () -> mecanum.driveFieldCentric(
                    x.getAsDouble(),
                    y.getAsDouble(),
                    rX.getAsDouble(),
                    imu.getRobotYawPitchRollAngles().getYaw()
        ));
    }

    public Command RobotCentricMecanum(double x, double y, double rX) {
        return new InstantCommand(() -> mecanum.driveRobotCentric(
                x,
                y,
                rX
        ));
    }
}
