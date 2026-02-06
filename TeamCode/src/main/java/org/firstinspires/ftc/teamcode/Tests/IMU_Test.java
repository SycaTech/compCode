package org.firstinspires.ftc.teamcode.Tests;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.Rev9AxisImu;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IMU_Test extends SubsystemBase {
    public Rev9AxisImu imu;

    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public IMU_Test(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

        imu = hardwareMap.get(Rev9AxisImu.class, "imu");
        imu.resetYaw();
    }

}
