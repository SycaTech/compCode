package org.firstinspires.ftc.teamcode.Teleops;

import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "imuTest")
public class imuTest extends OpMode {
    public Rev9AxisImu imu;

    @Override
    public void init() {
        imu = hardwareMap.get(Rev9AxisImu.class, "imu");
    }

    @Override
    public void loop() {
        telemetry.addData("yaw", imu.getRobotYawPitchRollAngles().getYaw());
    }
}
