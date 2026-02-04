package org.firstinspires.ftc.teamcode.extentions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="Motor RPM Checker", group="Utility")
public class MecanumRpmOpMode extends LinearOpMode{
    private DcMotorEx frontLeft, frontRight, backLeft, backRight;

    // 2. Ticks Per Revolution Constant
    // *** IMPORTANT: CHANGE THIS VALUE TO MATCH YOUR MOTOR'S SPECIFICATIONS ***
    // This value is the number of encoder ticks for one full revolution of the output shaft.
    // Example for goBILDA 5203 Series Yellow Jacket (19.2:1): 537.7
    private static final double TICKS_PER_REVOLUTION = 384.5;

    /**
     * Calculates the motor's RPM from its current velocity in encoder ticks per second.
     *
     * @param velocityTicksPerSecond The velocity returned by DcMotorEx.getVelocity()
     * @return The motor's speed in Revolutions Per Minute (RPM)
     */
    private double calculateRpm(double velocityTicksPerSecond) {
        // Formula: RPM = (Ticks/Second) / (Ticks/Revolution) * (60 Seconds/Minute)
        return (velocityTicksPerSecond / TICKS_PER_REVOLUTION) * 60.0;
    }

    @Override
    public void runOpMode() {
        // 3. Hardware Initialization
        // Ensure the motor names ("fl", "fr", "bl", "br") match your robot's configuration file
        try {
            frontLeft  = hardwareMap.get(DcMotorEx.class, "FL");
            frontRight = hardwareMap.get(DcMotorEx.class, "FR");
            backLeft   = hardwareMap.get(DcMotorEx.class, "BL");
            backRight  = hardwareMap.get(DcMotorEx.class, "BR");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Motor not found. Check hardware map names.");
            telemetry.update();
            sleep(5000);
            return;
        }

        // Set motor directions (adjust REVERSE/FORWARD as needed for your robot)
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to run without encoders (power control mode)
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Initialized. Waiting for Start.");
        telemetry.addData("Motor Ticks/Rev", TICKS_PER_REVOLUTION);
        telemetry.update();

        waitForStart();

        // 4. Main TeleOp Loop
        while (opModeIsActive()) {
            // Get D-pad input for full-power (1.0) movement
            double drive = 0.0;
            double strafe = 0.0;
            double turn = 0.0; // Turning is not controlled by D-pad

            if (gamepad1.dpad_up) {
                drive = 1.0;
            } else if (gamepad1.dpad_down) {
                drive = -1.0;
            }

            if (gamepad1.dpad_right) {
                strafe = 1.0;
            } else if (gamepad1.dpad_left) {
                strafe = -1.0;
            }

            // Mecanum drive calculations
            double flPower = drive + strafe + turn;
            double frPower = drive - strafe - turn;
            double blPower = drive - strafe + turn;
            double brPower = drive + strafe - turn;

            // Set motor power
            frontLeft.setPower(flPower * 0.969626);
            frontRight.setPower(frPower * 0.967);
            backLeft.setPower(blPower * 0.990453);
            backRight.setPower(brPower * 0.96849);

            // 5. RPM Calculation and Telemetry Update
            double flRpm = calculateRpm(frontLeft.getVelocity());
            double frRpm = calculateRpm(frontRight.getVelocity());
            double blRpm = calculateRpm(backLeft.getVelocity());
            double brRpm = calculateRpm(backRight.getVelocity());

            // Clear previous telemetry data
            telemetry.clearAll();

            // Display motor RPMs
            telemetry.addData("--- Motor RPMs ---", " ");
            telemetry.addData("Front Left (FL)", "%.2f RPM", flRpm);
            telemetry.addData("Front Right (FR)", "%.2f RPM", frRpm);
            telemetry.addData("Back Left (BL)", "%.2f RPM", blRpm);
            telemetry.addData("Back Right (BR)", "%.2f RPM", brRpm);
            telemetry.addData("------------------", " ");
            telemetry.addData("Drive Power", "D:%.2f | S:%.2f | T:%.2f", drive, strafe, turn);
            telemetry.addData("Status", "Running");

            // Send the updated data to the Driver Station
            telemetry.update();
        }
    }
}
