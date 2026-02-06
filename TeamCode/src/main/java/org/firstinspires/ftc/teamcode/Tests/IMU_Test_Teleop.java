package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "IMU_Test")
public class IMU_Test_Teleop extends CommandOpMode {
    private IMU_Test imuTest;
    @Override
    public void initialize() {
        imuTest = new IMU_Test(hardwareMap, telemetry);
    }
    @Override
    public void run() {
        telemetry.addData("yaw", imuTest.imu.getRobotYawPitchRollAngles().getYaw());
        telemetry.update();
    }
}
