package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SwerveModule {
    private DcMotorEx motor;
    private CRServo servo;
    private Lamprey2Encoder encoder;
    private static final double INV_PI = 1.0 / Math.PI;
    private static final double angleError = 1.5;
    public static PIDCoefficients servoPid = new PIDCoefficients(8e-3, 5e-15, 5e-15);

    private double calcError(double targetDegrees) {
        // We have to deal with the 'around the edge' checks
        double val = targetDegrees - this.getAngle();
        if (val > 180) {
            val -= 360;
        } else if (val < -180) {
            val += 360;
        }
        return val;
    }

    private double lastErr;
    private double lastInt;
    private ElapsedTime lastErrTime;
    private ElapsedTime totalTime;

    // Err is "# of degrees away from target"
    private double pidCalc(double err) {
        double seconds = lastErrTime.seconds();
        lastErrTime.reset();
        double de_dt = (err - lastErr) / seconds; // derivative
        double e_t = lastInt + err * seconds; // integral
        lastInt = e_t;
        double value = servoPid.p * err + servoPid.i * e_t + servoPid.d * de_dt;
        return Math.max(-1.0, Math.min(1.0, value));
    }

    public void turnToDegrees(double angle, Telemetry telemetry) {
        lastErrTime.reset();
        totalTime.reset();
        double val = -2.0;
        telemetry.addData("Starting:", angle);
        int count = 0;
        for (double err = calcError(angle);
             Math.abs(err) > angleError && totalTime.milliseconds() < 2000;
             err = calcError(angle), count++) {
            val = pidCalc(err);
            servo.setPower(val);
            if (count < 11)
                telemetry.addData("Err->val", "%3.0f->%1.4f", err, val);
        }
        servo.setPower(0);
        telemetry.update();
        servo.setPower(0);
    }

    public double getAngle() {
        return encoder.getAngle();
    }

    public void turnToRadians(double angle, Telemetry telemetry) {
        turnToDegrees(angle * 180 * INV_PI, telemetry);
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
        encoder = new Lamprey2Encoder(hw, name + "Encoder");
        lastErr = 0;
        lastInt = 0;
        lastErrTime = new ElapsedTime();
        totalTime=new ElapsedTime();
    }

    public void manualDriveDegrees(double power, int angle) {
        // double servoError = goToServo(angle);
    }

    public void manualDriveRadians(double power, double angle) {
        manualDriveDegrees(power, (int) Math.round(angle * 180 / Math.PI));
    }
}
