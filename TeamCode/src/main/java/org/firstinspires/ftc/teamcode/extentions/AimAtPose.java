package org.firstinspires.ftc.teamcode.extentions;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.util.InterpLUT;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.LimeLight;

public class AimAtPose extends SubsystemBase {
    private InterpLUT lut;
    private LimeLight LIMELIGHT;
    private LLResult llResult;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    private double tagID;
    public double neededRPM;
    private double distance;

    public AimAtPose(HardwareMap hwMap, Telemetry telemetry) {
        this.hardwareMap = hwMap;
        this.telemetry = telemetry;
        LIMELIGHT = new LimeLight(this.hardwareMap, this.telemetry);

        lut = new InterpLUT();
        lut.add(50, 950);
        lut.add(100, 1000);
        lut.add(200, 1100);
        lut.add(300, 1250);
        lut.createLUT();
    }

    @Override
    public void periodic() {
        llResult = LIMELIGHT.limelight.getLatestResult();
        distance = getDistanceFromTage(llResult.getTa());
        tagID = llResult.getFiducialResults().get(0).getFiducialId();
        getRPM(this.distance);
        telemetry.addData("distance", distance);
        telemetry.addData("tag ID", tagID);
        telemetry.update();
    }

    public void getRPM(double distance) {
        neededRPM =  lut.get(distance);
        telemetry.addData("RPM Needed", neededRPM);
    }

    private static final double K = 1.756760881;

    public double getDistanceFromTage(double ta) {
        ta = Math.max(ta, 0.01);
        return (K / Math.sqrt(ta)) * 100 + 16;
    }

    public Command calculateRPM() {
        return new RunCommand(() ->getRPM(this.distance));
    }
}