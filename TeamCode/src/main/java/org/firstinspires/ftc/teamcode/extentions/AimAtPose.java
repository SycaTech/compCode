package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;

public class AimAtPose extends SubsystemBase {
    private InterpLUT lut;

    public AimAtPose() {
        lut = new InterpLUT();

        lut.add(0.1, 1000);
        lut.add(0.5, 1150);
        lut.add(1.0, 1300);
        lut.add(2.0, 1400);
        lut.add(3.0, 1620);
        lut.createLUT();
    }

    public double getRPM(double distance) {
        return lut.get(distance);
    }
}
