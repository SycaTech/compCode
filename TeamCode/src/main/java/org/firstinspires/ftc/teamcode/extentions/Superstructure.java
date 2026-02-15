package org.firstinspires.ftc.teamcode.extentions;

import android.health.connect.datatypes.units.Power;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

public class Superstructure extends SubsystemBase {
    public Shooter shooter;
    public Intake intake;

    public Superstructure(Shooter shooter, Intake intake) {
        this.shooter = shooter;
        this.intake = intake;
    }
    public SequentialCommandGroup ST(){
        return new SequentialCommandGroup(
                new InstantCommand(() -> intake.setpower(0.2)),
                new InstantCommand(() -> shooter.Power(2350) )
        );
    }


}
