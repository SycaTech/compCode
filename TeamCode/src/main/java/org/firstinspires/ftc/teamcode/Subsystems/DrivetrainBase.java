package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.extentions.Constants;

import java.util.function.DoubleSupplier;

public class DrivetrainBase extends SubsystemBase {
    private final Motor frontRight;
    private final Motor backLeft;
    private final Motor frontLeft;
    private final Motor backRight;




    public Rev9AxisImu imu;
    public PIDController pid = new PIDController(Constants.Mecanum.kP, Constants.Mecanum.kI, Constants.Mecanum.kD);

    public MecanumDrive mecanum;
    Telemetry telemetry;

    public DrivetrainBase(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        frontLeft = new Motor(hwMap, Constants.Mecanum.frontLeftName);
        frontRight = new Motor(hwMap, Constants.Mecanum.frontRightName);
        backLeft= new Motor(hwMap, Constants.Mecanum.backLeftName);
        backRight = new Motor(hwMap, Constants.Mecanum.backRightName);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.setRunMode(Motor.RunMode.RawPower);
        frontRight.setRunMode(Motor.RunMode.RawPower);
        backLeft.setRunMode(Motor.RunMode.RawPower);
        backRight.setRunMode(Motor.RunMode.RawPower);

        frontLeft.setInverted(true);
        backLeft.setInverted(true);

        imu = hwMap.get(Rev9AxisImu.class, Constants.Mecanum.imuName);

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public Command FieldCentricMecanum(DoubleSupplier y, DoubleSupplier x, DoubleSupplier rx) {
        return new RunCommand(
                () -> mecanum.driveFieldCentric(
                        x.getAsDouble(),
                        y.getAsDouble(),
                        rx.getAsDouble(),
                        imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)
                ), this
        );
    }

    public Command RobotCentricMecanum(DoubleSupplier x, DoubleSupplier y, DoubleSupplier rX) {
        return new RunCommand(() -> mecanum.driveRobotCentric(
                x.getAsDouble(),
                y.getAsDouble(),
                rX.getAsDouble()
        ), this);
    }

    @Override
    public void periodic() {
//        pid.calculate(
//                frontLeft.getCurrentPosition(), pid.getSetPoint()
//        );
//
//        pid.calculate(
//                frontRight.getCurrentPosition(), pid.getSetPoint()
//        );
//
//        pid.calculate(
//                backLeft.getCurrentPosition(), pid.getSetPoint()
//        );
//
//        pid.calculate(
//                backRight.getCurrentPosition(), pid.getSetPoint()
//        );

        telemetry.addData("yaw", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
    }
}
