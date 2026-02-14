package org.firstinspires.ftc.teamcode.Subsystems;
import static org.firstinspires.ftc.teamcode.extentions.Constants.shoot.TICKS_PER_REVOLUTION;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.extentions.Constants;

public class Shooter extends SubsystemBase {
    public DcMotorEx master;
    public DcMotorEx slave;
    public double P;



    public Telemetry telemetry;

    PIDFController pidf = new PIDFController
            (Constants.shoot.kP,
                    Constants.shoot.kI,
                    Constants.shoot.kD,
                    0);

    public Shooter(HardwareMap hwMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        master = hwMap.get(DcMotorEx.class, "Master");
        slave = hwMap.get(DcMotorEx.class, "Slave");
        master.setDirection(DcMotorSimple.Direction.FORWARD);
        slave.setDirection(DcMotorSimple.Direction.REVERSE);

        pidf.setTolerance(3);
    }

    @Override
    public void periodic() {
        double P = pidf.calculate(
                master.getCurrentPosition(),
                slave.getCurrentPosition()
        );
        telemetry.addData("masterRPM" , getmasterRPM());
        telemetry.addData("slaveRPM" , getslaveRPM());
    }


    public void Power(double power){
        this.P = power;
        master.setVelocity(power);
        slave.setVelocity(power);
    }
    public double getmasterRPM() {
        return (master.getVelocity() / TICKS_PER_REVOLUTION) * 60.0;
    }
    public double getslaveRPM() {
        return (slave.getVelocity() / TICKS_PER_REVOLUTION) * 60;
    }


    public Command power(double vel) {
        return new RunCommand(() -> Power(vel), this);
    }
}





