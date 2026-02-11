package org.firstinspires.ftc.teamcode.Subsystems;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
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
    public double target = 0;
    private double output;


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
        pidf.calculate(
                master.getCurrentPosition(),
                slave.getCurrentPosition()
        );

        telemetry.addData("masterRPM" , master.getVelocity());
        telemetry.addData("slaveRPM" , slave.getVelocity());
    }

    public Command setShootRPM(double vel) {
        return new InstantCommand(() -> {
            master.setVelocity(vel);
            slave.setVelocity(vel);
        }, this);
    }
}