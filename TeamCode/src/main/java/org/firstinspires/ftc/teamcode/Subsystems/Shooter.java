package org.firstinspires.ftc.teamcode.Subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.extentions.Constants;
public class Shooter extends SubsystemBase {
    public Motor master;
    public Motor slave;

    PIDFController pidf = new PIDFController
            (Constants.shoot.kP,
            Constants.shoot.kI,
            Constants.shoot.kD,
            0);

    public Shooter(HardwareMap hardwareMap) {
        master = new Motor(hardwareMap, "master");
        slave = new Motor(hardwareMap, "slave");

        master.resetEncoder();
        slave.resetEncoder();
    }

    @Override
    public void periodic() {
        double output = pidf.calculate(
                master.getCurrentPosition()
        );

        Constants.shoot.telemetry.addData("Master RPM", getMasterRPM());
        Constants.shoot.telemetry.addData("Slave RPM", getMasterRPM());
    }

    public double getMasterRPM() {
    master.getCorrectedVelocity();
        return ( Constants.shoot.targetVelocity/ Constants.shoot.TICKS_PER_REVOLUTION) * 60.0;

    }

    public double getSlaveRPM(){
        slave.getCorrectedVelocity();
        return (Constants.shoot.targetVelocity/ Constants.shoot.TICKS_PER_REVOLUTION) *60;

    }
}