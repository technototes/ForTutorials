package org.firstinspires.ftc.teachcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SwerveModule {
    private DcMotorEx motor;
    private CRServo servo;
    private Lamprey2Encoder encoder;
    private static final double INV_PI = 1.0 / Math.PI;
    private static final double angleError = 1.5;
    public static PIDCoefficients servoPid = new PIDCoefficients(.01, .01, .01);

    private double calcError(double targetDegrees) {
        return this.getAngle() - targetDegrees;
    }

    private double lastErr;
    private ElapsedTime lastErrTime;

    // Err is "# of degrees away from target"
    private double pidCalc(double err) {
        double seconds = lastErrTime.seconds();
        lastErrTime.reset();
        double de_dt = (err - lastErr) / seconds; // derivative
        double e_t = (err + lastErr) * 0.5 * seconds; // integral
        double value = servoPid.p * err + servoPid.i * e_t + servoPid.d * de_dt;
        return Math.max(-1.0, Math.min(1.0, value));
    }

    public void turnToDegrees(double angle) {
        lastErrTime.reset();
        for (double err = calcError(angle); Math.abs(err) < angleError; err = calcError(angle)) {
            servo.setPower(pidCalc(err));
        }
        servo.setPower(0);
    }

    public double getAngle() {
        return encoder.getAngle();
    }

    public void turnToRadians(double angle) {
        turnToDegrees(angle * 180 * INV_PI);
    }

    public void setRotatePower(double d) {
        servo.setPower(d);
    }

    public void setDrivePower(double d) {
        motor.setPower(d);
    }

    public SwerveModule(HardwareMap hw, String name) {
        motor = hw.get(DcMotorEx.class, name + "Motor");
        servo = hw.get(CRServo.class, name + "Servo");
        encoder = hw.get(Lamprey2Encoder.class, name + "Encoder");
        lastErr = 0;
        lastErrTime = new ElapsedTime();
    }

    public void manualDriveDegrees(double power, int angle) {
        // double servoError = goToServo(angle);
    }

    public void manualDriveRadians(double power, double angle) {
        manualDriveDegrees(power, (int) Math.round(angle * 180 / Math.PI));
    }
}
