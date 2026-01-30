package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.Constants;

public class DrivetrainBase extends SubsystemBase {
    private final Motor frontLeft;
    private final Motor frontRight;
    private final Motor backLeft;
    private final Motor backRight;

    public GamepadEx driverController;

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
        this.driverController = driverController;
    }

    public Command FieldCentricMecanum() {
        return new InstantCommand(() -> mecanum.driveFieldCentric(
                driverController.getLeftX(),
                driverController.getLeftY(),
                driverController.getRightX(),
                imu.getRobotYawPitchRollAngles().getYaw()
        ));
    }

    public Command RobotCentricMecanum() {
        return new InstantCommand(() -> mecanum.driveRobotCentric(
                driverController.getLeftX(),
                driverController.getLeftY(),
                driverController.getRightX()
        ));
    }
}
