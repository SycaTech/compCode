package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DrivetrainBase extends SubsystemBase {
    private Motor frontLeft, frontRight, backLeft, backRight;
    public RevIMU imu;
    public MecanumDrive mecanum;

    public DrivetrainBase(HardwareMap hwMap) {
        frontLeft = new Motor(hwMap, "FL", Motor.GoBILDA.RPM_435);
        frontRight = new Motor (hwMap, "FR", Motor.GoBILDA.RPM_435);
        backLeft = new Motor(hwMap, "BL", Motor.GoBILDA.RPM_435);
        backRight = new Motor(hwMap, "BR", Motor.GoBILDA.RPM_435);

        imu = new RevIMU(hwMap);
        imu.init();

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void loop(Telemetry telemetry) {
        telemetry.addData("yaw", imu.getRotation2d().getDegrees());
    }
}
