package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor extends SubsystemBase {
    NormalizedColorSensor colorSensor;
    Telemetry telemetry;
    public enum DETECTED_COLOR {
        BALL_IN,
        NO_BALL
    }

    public ColorSensor(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
        colorSensor = hwMap.get(NormalizedColorSensor.class, "colorSensor");
        colorSensor.setGain(4);
    }

    public DETECTED_COLOR getDetectedColor() {
        NormalizedRGBA colors =colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        if (normRed > 0.1 && normGreen > 0.1 && normBlue > 0.24) {
            return DETECTED_COLOR.BALL_IN;
        }

        return DETECTED_COLOR.NO_BALL;
    }

    @Override
    public void periodic() {
        telemetry.addData("Color Detected", getDetectedColor());
    }
}
