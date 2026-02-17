package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.extentions.ColorSensor;

public class ColorSensorTeleop extends CommandOpMode {
    private ColorSensor colorSensor;

    @Override
    public void initialize() {
        colorSensor = new ColorSensor(telemetry, hardwareMap);
    }

    @Override
    public void run() {
        super.run();
        telemetry.update();
    }
}
