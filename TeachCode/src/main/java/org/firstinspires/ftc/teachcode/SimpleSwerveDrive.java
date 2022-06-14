package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


// This is just a simple, stupid swerve control system
// It allows you to *either* drive in a particular direction
// or rotate the bot at a given speed
// but *NOT BOTH AT THE SAME TIME*
public class SimpleSwerveDrive {
    private static double FL_RR_ROT_ANGLE = -45.0;
    private static double FR_RL_ROT_ANGLE = 45.0;
    private static int TRANSITION_DELAY_MS = 100;

    private boolean isRotating;
    private SwerveController fl, fr, rl, rr;

    private static void Delay() {
        try {
            Thread.sleep(TRANSITION_DELAY_MS);
        } catch (Exception e) {
        }
    }

    private void setPower(double flp, double frp, double rlp, double rrp) {
        fl.setPower(flp);
        fr.setPower(frp);
        rl.setPower(rlp);
        rr.setPower(rrp);
    }

    private void setDegrees(double flp, double frp, double rlp, double rrp) {
        fl.setDegrees(flp);
        fr.setDegrees(frp);
        rl.setDegrees(rlp);
        rr.setDegrees(rrp);
    }

    private void setRadians(double flp, double frp, double rlp, double rrp) {
        fl.setRadians(flp);
        fr.setRadians(frp);
        rl.setRadians(rlp);
        rr.setRadians(rrp);
    }

    private void setRotating(boolean state) {
        if (state != isRotating) {
            isRotating = true;
            Delay();
        }
    }

    public SimpleSwerveDrive(DcMotorEx flm, DcMotorEx frm, DcMotorEx rlm, DcMotorEx rrm, Servo fls, Servo frs, Servo rls, Servo rrs) {
        fl = new SwerveController(flm, fls);
        fr = new SwerveController(frm, frs);
        rl = new SwerveController(rlm, rls);
        rr = new SwerveController(rrm, rrs);
        isRotating = false;
    }

    public void setDirectionAndPowerDegrees(double power, double angle) {
        setDegrees(angle, angle, angle, angle);
        setRotating(false);
        setPower(power, power, power, power);
    }

    public void setDirectionAndPowerRadians(double power, double angle) {
        setRadians(angle, angle, angle, angle);
        setRotating(false);
        setPower(power, power, power, power);
    }

    public void setRotationSpeed(double speed) {
        if (!isRotating) {
            setDegrees(FL_RR_ROT_ANGLE, FR_RL_ROT_ANGLE, FR_RL_ROT_ANGLE, FL_RR_ROT_ANGLE);
            setRotating(true);
        }
        setPower(-speed, speed, -speed, speed);
    }

    public void stop() {
        setPower(0, 0, 0, 0);
    }
}
