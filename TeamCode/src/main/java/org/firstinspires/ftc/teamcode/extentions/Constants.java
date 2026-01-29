package org.firstinspires.ftc.teamcode.extentions;

import com.bylazar.configurables.annotations.Configurable;

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
}
