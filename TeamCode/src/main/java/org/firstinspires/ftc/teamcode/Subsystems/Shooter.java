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

        telemetry.addData("masterRPM" , P);
        telemetry.addData("slaveRPM" , P);
        telemetry.addData("target" , target);
    }

    public Command power(double vel){
        return new InstantCommand(() -> {
            double P = pidf.calculate(
                    master.getCurrentPosition(),
                    slave.getCurrentPosition()
            );
            this.P = vel;
            this.target = vel;
            master.setPower(vel);
            slave.setPower(vel);
        }
        );}
}





