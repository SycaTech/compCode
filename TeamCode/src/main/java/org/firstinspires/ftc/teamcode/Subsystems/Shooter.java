package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.KD;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.KI;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.KP;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.MAX_INTEGRAL;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.MAX_POWER;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.TICKS_PER_REVOLUTION;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.integral;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.previousError;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.targetVelocity;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.Constants;

import java.util.concurrent.Callable;

public class Shooter extends SubsystemBase{
    public Motor master;
    public Motor slave;
   public PIDFController pidf;





    public Shooter(HardwareMap hardwareMap){
         master = new Motor(hardwareMap, "master");
         slave = new Motor(hardwareMap, "slave");
        this.pidf = new PIDFController(KP, KI , KD , 0);
        pidf.setP(0.2);
        pidf.setI(0.05);
        pidf.setD(0);
        KP = pidf.getP();
        KI = pidf.getI();
        KD = pidf.getD();




        double[] coeffs = pidf.getCoefficients();
        KP = coeffs[0];
        KI = coeffs[1];
        KD = coeffs[2];

        master.resetEncoder();
        slave.resetEncoder();

        int posM = master.getCurrentPosition();
        int posS = slave.getCurrentPosition();

        double velocity = master.getCorrectedVelocity();
        double velocity1 = slave.getCorrectedVelocity();

        Motor.Encoder encoder = master.encoder;
        Motor.Encoder encoder1 = slave.encoder;

        double revolutions = encoder.getRevolutions();
        double revolutions1 = encoder1.getRevolutions();

        encoder.setDistancePerPulse(18.0);

        double distance = encoder.getDistance();
        master.stopAndResetEncoder();
        slave.stopAndResetEncoder();
        slave.setRunMode(Motor.RunMode.PositionControl);
        master.setRunMode(Motor.RunMode.PositionControl);


    }
    @Override
    public void periodic() {
        if (master == null || slave == null) return;
        if (targetVelocity == 0) {
            master.set(0);
            slave.set(0);
            integral = 0;
            previousError = 0;
            return;
        }
        double currentRPM = RPM();
            pidf.setPIDF(KP, KI, KD , 0);
            double output = pidf.calculate(currentRPM, targetVelocity);
            master.getCurrentPosition();
            slave.getCurrentPosition();
            pidf.setSetPoint(1200);
            master.set(output);
            slave.set(output);
        }




    public void setPower(double power) {
        if (master != null && slave != null) {
            master.set(power);

        }
    }
    public void stop() {
        setPower(0);
        targetVelocity = 0;
    }
    public void setTargetVelocity(double velocity) {
        targetVelocity = velocity;

    }
    public Command setTargetVelocityCommand(double velocity) {
        return new InstantCommand(() -> setTargetVelocity(velocity));
    }
    public double getTargetVelocity() {
        return targetVelocity;
    }
    public double RPM() {
        if (master == null) return 0;
        double velocityTPS = master.getCorrectedVelocity();
        return (velocityTPS / TICKS_PER_REVOLUTION) * 60.0;
    }
    public Command setVel(double vel){
        return new InstantCommand(()-> master.set(vel))
                .andThen(new InstantCommand(()-> slave.set(vel)));
    }

    public double SLAVERPM() {
        if (slave == null) return 0;
        double velocityTPS = slave.getCorrectedVelocity();
        return (velocityTPS / TICKS_PER_REVOLUTION) * 60.0;
    }

    public void debugTelemetry(org.firstinspires.ftc.robotcore.external.Telemetry telemetry)
    {
        telemetry.addData("Master RPM", RPM());
        telemetry.addData("Slave RPM", SLAVERPM());
        telemetry.addData("Target Velocity", targetVelocity);
    }
}






