package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Intake extends SubsystemBase {
    private Telemetry telemetry;

    private DcMotorEx intake;
    private DcMotorEx FR;

    public Intake(Telemetry telemetry, HardwareMap hwMap) {
        this.telemetry = telemetry;
//        intake = hwMap.get(DcMotorEx.class, "intake");
        FR = hwMap.get(DcMotorEx.class , "INTAKE");

//        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    final double TICKS_PER_REVOLUTION = 103.6;

//    @Override
//    public void periodic() {
//        double velocityTPSL = intake.getVelocity();
//        double velocityFR = FR.getVelocity();
//        double velocityRPML = (velocityTPSL / TICKS_PER_REVOLUTION) * 60.0;
//
//        telemetry.addData("power", intake.getPower());
//        telemetry.addData("powerF" , FR.getPower());
//
//    }

    public void INTAKEsetPower(double power) {
        intake.setPower(power);

    }
    public void FRsetPower(double p){
        FR.setPower(p);
    }

    public Command Power(double VOLT) {
        return new InstantCommand(() -> INTAKEsetPower(VOLT));
    }
    public Command fPower(double vol){
        return new InstantCommand(() -> FRsetPower(vol));
    }
}
