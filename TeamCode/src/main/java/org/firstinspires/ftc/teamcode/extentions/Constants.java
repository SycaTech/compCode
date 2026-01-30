package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Configurable
public class Constants {
    public static class Mecanum{
        public static final String frontLeftName = "FL";
        public static final String frontRightName = "FR";
        public static final String backLeftName = "BL";
        public static final String backRightName = "BR";

        public static final double kP = 0.01;
        public static final double kI = 0.0;
        public static final double kD = 0.0;
    }

    public static class shoot{
        public static final double TICKS_PER_REVOLUTION = 103.6;
        public static  double kP = 0.2;
        public static double kI = 0.05;
        public static  double kD = 0;
        public static final double kF = 0;
        public static double MAX_INTEGRAL = 100;
        public static  double MAX_POWER = 1.0;
        public Motor master;
        public Motor slave;
        public static Telemetry telemetry;
        public static double targetVelocity = 0.0;


    }
}
