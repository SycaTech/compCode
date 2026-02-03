package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;

public class AimAtPose extends SubsystemBase {
    private InterpLUT lut;

    public AimAtPose() {
        lut = new InterpLUT();

        lut.add(50, 950);
        lut.add(100, 1000);
        lut.add(200, 1100);
        lut.add(300, 1250);
        lut.createLUT();
    }

    public double getRPM(double distance) {
        return lut.get(distance);
    }
}