package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class shoot extends SubsystemBase {
    private DcMotorEx master;
    private DcMotorEx slave;

    private String masterName = "Master";
    private String slaveName = "Slave";

    private Telemetry telemetry;
    final double TICKS_PER_REVOLUTION = 103.6;

    private double targetRPM = 0;
    private final double RPM_TOLERANCE = 60;

    public shoot(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        master = hardwareMap.get(DcMotorEx.class, masterName);
        slave = hardwareMap.get(DcMotorEx.class, slaveName);

        master.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slave.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        master.setDirection(DcMotorSimple.Direction.FORWARD);
        slave.setDirection(DcMotorSimple.Direction.REVERSE);

        master.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slave.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        master.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slave.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    private double rpmToTicksPerSec(double rpm) {
        return (rpm * TICKS_PER_REVOLUTION) / 60.0;
    }

    public double getVelocityRPM() {
        return (master.getVelocity() / TICKS_PER_REVOLUTION) * 60.0;
    }

    public boolean isAtTargetSpeed() {
        return Math.abs(Math.abs(getVelocityRPM()) - Math.abs(targetRPM)) < RPM_TOLERANCE;
    }

    public Command setShootRPM(double rpm){
        return new InstantCommand(() -> {
            this.targetRPM = rpm;
            double ticksPerSec = rpmToTicksPerSec(rpm);
            master.setVelocity(ticksPerSec);
            slave.setVelocity(ticksPerSec);
        }, this);
    }

    public Command shootBall(int rpm){
        return setShootRPM(rpm);
    }

    public Command waitForShooterSpeed(){
        return new WaitUntilCommand(this::isAtTargetSpeed);
    }

    public void setPower(double power) {
        master.setPower(power);
        slave.setPower(power);
    }

    public Command power(double power){
        return new InstantCommand(()-> setPower(power));
    }

    public Command zerod(){
        return new InstantCommand(()-> {
            targetRPM = 0;
            master.setVelocity(0);
            slave.setVelocity(0);
            setPower(0);
        }, this);
    }

    @Override
    public void periodic(){
        telemetry.addData("Shooter Target RPM", targetRPM);
        telemetry.addData("Shooter Current RPM", getVelocityRPM());
        telemetry.addData("Shooter Ready?", isAtTargetSpeed());
        telemetry.addData("Master Power", master.getPower());
    }
}