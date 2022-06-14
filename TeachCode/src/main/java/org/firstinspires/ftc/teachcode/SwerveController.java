package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SwerveController {
    // At 4.8v, no load 60 degrees takes super-speed serves .055 seconds to move 60 degrees
    // They're faster at more volts, so 195ms + an extra 55mm for "load" consideration:
    private static int DIR_TRANSITION_DELAY_MS = 250;
    private static double INV_PI = 1.0 / MATH.PI;
    private static double INV_180 = 1.0 / 180.0;
    private DcMotorEx mfl, mfr, mrl, mrr;
    // A bunch of stuff gets *much* easier when we can use CRServo's with encoders :)
    private Servo sfl, sfr, srl, srr;
    private boolean flFlip, frFlip, rlFlip, rrFlip;

    ElapsedTime timer;

    private static void Delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    // Force a wrap-around value (like degrees or radians) to be within low/high
    private static double inRange(double low, double high, double val) {
        // Dumb & slow, but easy, and visually correct...
        while (val < low) {
            val += (high - low);
        }
        while (val >= high) {
            val -= (high - low);
        }
        return val;
    }

    static boolean needFlip(double anglish) {
        return anglish < 0.0;
    }

    // The angles for this function are a scale from -1 to +1 meaning -180 degrees to +180 degrees
    // But since we can only move 180 degrees, we may have to invert the wheel motor...
    public void setControlRelative(double flp, double fla, double frp, double fra, double rlp, double rla, double rrp, double rra) {
        // See if *any* of the servos require 'flipping'
        boolean ffl = (needFlip(fla) != flFlip);
        boolean ffr = (needFlip(fra) != frFlip);
        boolean frl = (needFlip(rla) != rlFlip);
        boolean frr = (needFlip(rra) != rrFlip);
        boolean somethingFlipping = (ffl || ffr || frl || frr);
        double delay = 0.0;
        if (somethingFlipping) {
            // If we're flipping a servo, we have to stop the bot so it doesn't freak out
            // while the servos are moving a *lot*
            mfl.setPower(0);
            mfr.setPower(0);
            mrl.setPower(0);
            mrr.setPower(0);
            // Start moving the servos to their new locations (often times, nearly 180...)
            delay = DIR_TRANSITION_DELAY_MS;
            flFlip = needFlip(fla);
            frFlip = needFlip(fra);
            rlFlip = needFlip(rla);
            rrFlip = needFlip(rra);
        }
        timer.reset();
        // Set all 4 servos to the correct position, dealing with 'flips'
        if (flFlip) fla += 1;
        if (frFlip) fra += 1;
        if (rlFlip) rla += 1;
        if (rrFlip) rra += 1;
        // Now set all the servo's angles
        sfl.setPosition(fla);
        sfr.setPosition(fra);
        srl.setPosition(rla);
        srr.setPosition(rra);
        // We need to wait until the wheels get to the right point, now
        while (timer.milliseconds() < delay) {
            // Sneak up on it, because why not?
            Delay((int) (delay - timer.milliseconds()) / 2);
        }
        // Now, make the motors go in the right direction :)
        mfl.setPower(flFlip ? -flp : flp);
        mfr.setPower(frFlip ? -frp : frp);
        mrl.setPower(rlFlip ? -rlp : rlp);
        mrr.setPower(rrFlip ? -rrp : rrp);
    }

    public SwerveController(DcMotorEx flm, DcMotorEx frm, DcMotorEx rlm, DcMotorEx rrm, Servo fls, Servo frs, Servo rls, Servo rrs) {
        m = motor;
        s = servo;
        // Until we get encoders, we can use the servo's in normal mode
        s.scaleRange(.1666666666, .833333333);
        multiplier = 1.0;
        timer = new ElapsedTime();
    }

    public void setControlRadians(double flp, double fla, double frp, double fra, double rlp, double rla, double rrp, double rra) {
        fra = inRange(-Math.PI, Math.PI, fra) * INV_PI;
        fla = inRange(-Math.PI, Math.PI, fla) * INV_PI;
        rra = inRange(-Math.PI, Math.PI, rra) * INV_PI;
        rla = inRange(-Math.PI, Math.PI, rla) * INV_PI;
        setControlRelative(flp, fla, frp, fra, rlp, rla, rrp, rra);
    }

    public void setControlDegrees(double flp, double fla, double frp, double fra, double rlp, double rla, double rrp, double rra) {
        fra = inRange(-180, 180, fra) * INV_180;
        fla = inRange(-180, 180, fla) * INV_180;
        rra = inRange(-180, 180, rra) * INV_180;
        rla = inRange(-180, 180, rla) * INV_180;
        setControlRelative(flp, fla, frp, fra, rlp, rla, rrp, rra);
    }
}
