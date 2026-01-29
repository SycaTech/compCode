package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter extends SubsystemBase {
    public Motor slave;
    public Motor master;

    private double targetVelocity = 0;
    private double integral = 0;
    private static final double MAX_INTEGRAL = 100;
    private static final double MAX_POWER = 1.0;
    private double previousError = 0;
    public double kP;
    public double kD;
    public double kI;
    public double kF;




    public Shooter(HardwareMap hardwareMap){
        Motor master = new Motor(hardwareMap, "master");
        Motor slave = new Motor(hardwareMap, "slave");
        PIDFController pidf = new PIDFController(kP, kI, kD, kF);
        pidf.setP(0.2);
        pidf.setI(0.05);
        pidf.setD(0);
        kP = pidf.getP();
        kI = pidf.getI();
        kD = pidf.getD();

// set all gains
        pidf.setPIDF(kP, kI, kD , 0);

        // get all gain coefficients
        double[] coeffs = pidf.getCoefficients();
        kP = coeffs[0];
        kI = coeffs[1];
        kD = coeffs[2];
        kF = coeffs[3];




    }




}


