package org.firstinspires.ftc.teamcode.Subsystems;


import android.graphics.Color;
import com.arcrobotics.ftclib.hardware.SensorColor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Color Sensor Fixed")
public class ColorSensorFinal extends LinearOpMode {

    private SensorColor colorSensor;

    @Override
    public void runOpMode() {
        colorSensor = new SensorColor(hardwareMap, "sensor_color");
        float[] hsv = new float[3];

        waitForStart();

        while (opModeIsActive()) {
            Color.RGBToHSV(colorSensor.red(), colorSensor.green(), colorSensor.blue(), hsv);
            float h = hsv[0];
            float s = hsv[1];

            String result = "None";

            if (s > 0.2) {
                if (h >= 250 && h <= 310) result = "Purple";
                else if (h >= 80 && h <= 150) result = "Green";
            }

            telemetry.addData("Detected", result);
            telemetry.addData("H", h);
            telemetry.addData("S", s);
            telemetry.update();
        }
    }
}