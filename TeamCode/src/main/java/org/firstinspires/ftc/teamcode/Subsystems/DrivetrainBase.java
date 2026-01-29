package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivetrainBase extends SubsystemBase {
    private Motor frontLeft, frontRight, backLeft, backRight;
    public Rev9AxisImu imu;
    public MecanumDrive mecanum;

    public DrivetrainBase(HardwareMap hwMap) {
        frontLeft = new Motor(hwMap, "FL", Motor.GoBILDA.RPM_435);
        frontRight = new Motor (hwMap, "FR", Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hwMap, "BL", Motor.GoBILDA.RPM_435);
        backRight = new Motor(hwMap, "BR", Motor.GoBILDA.RPM_435);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.setInverted(true);
        Left.setInverted(true);

        imu = hwMap.get(Rev9AxisImu.class, "imu");
        imu.resetYaw();

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void loop(Telemetry telemetry) {
        telemetry.addData("yaw", imu.getRobotYawPitchRollAngles().getYaw());

        telemetry.addData("front Left", frontLeft.getRate());
        telemetry.addData("front Right", frontRight.getRate());
        telemetry.addData("back Left", backLeft.getRate());
        telemetry.addData("back Right", backRight.getRate());

    }
}
