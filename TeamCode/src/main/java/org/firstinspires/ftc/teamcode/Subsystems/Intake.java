package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private Telemetry telemetry;
    public static Intake INSTANCE = new Intake();

    public Intake() {

    }

    public DcMotorEx intake;

    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotorEx.class, "intake");
    }

    final double TICKS_PER_REVOLUTION = 103.6;

    public void periodic(Telemetry telemetry) {
        double velocityTPSL = intake.getVelocity();
        double velocityRPML = (velocityTPSL / TICKS_PER_REVOLUTION) * 60.0;

        telemetry.addData("power", intake.getPower());
        telemetry.update();

    }

    public void setPower(double power) {
        intake.setPower(power);
    }

    public Command Power(double VOLT) {
        return new InstantCommand(() -> setPower(VOLT));
    }
}