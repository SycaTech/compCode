package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor extends SubsystemBase {
    NormalizedColorSensor clrSensor;
    HardwareMap hardwareMap;
    Telemetry telemetry;

    public enum DetectedColor{
        PURPLE,
        GREEN,
        UNKNOWN
    }

    public ColorSensor(HardwareMap hwMap, Telemetry telemetry) {
        this.hardwareMap = hwMap;
        this.telemetry = telemetry;
        clrSensor = hwMap.get(NormalizedColorSensor.class, "colorSensor");
        clrSensor.setGain(4);
    }

    public DetectedColor getDetectedColor() {
        NormalizedRGBA colors = clrSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        if (normRed < 0.05 && normGreen < 0.18 && normBlue < 0.16) {
            return DetectedColor.PURPLE;
        } else if (normRed < 0.04 && normGreen < 0.15 && normBlue < 0.13) {
            return DetectedColor.GREEN;
        }

        return DetectedColor.UNKNOWN;
    }

}
