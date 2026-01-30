package org.firstinspires.ftc.teamcode.extentionsTeleops;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.ColorSensor;

@TeleOp(name = "ColorSensor")
public class ColorSensorTeleop extends OpMode {
    public ColorSensor clr;
    ColorSensor.DetectedColor detectedColor;

    @Override
    public void init() {
        clr = new ColorSensor(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        detectedColor = clr.getDetectedColor();
        telemetry.addData("Color detected", detectedColor);
    }
}
