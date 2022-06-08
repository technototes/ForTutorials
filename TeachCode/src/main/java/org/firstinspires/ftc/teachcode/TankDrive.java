package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import java.util.*;

public class TankDrive {
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public DcMotorEx motorL;
    public DcMotorEx motorR;

    public TankDrive(DcMotorEx motorL, DcMotorEx motorR) {
        this.motorL = motorL;
        this.motorR = motorR;
    }

    // For non-stick control system
    public void moveForwardButton(double power, int seconds) {
        motorL.setPower(power);
        motorR.setPower(power);
        sleep(seconds * 1000);
        pleaseStop();
    }

    // For non-stick control system
    public void pleaseStop() {
        motorL.setPower(0);
        motorR.setPower(0);
    }

    // For non-stick control system
    public void moveBackwardButton(double power, int seconds) {
        motorL.setPower(-power);
        motorR.setPower(-power);
        sleep(seconds * 1000);
        pleaseStop();
    }

    // For stick control system
    public void motorLPower(double power) {
        motorL.setPower(power);
    }

    // For stick control system
    public void motorRPower(double power) {
        motorR.setPower(power);
    }

    public DcMotorEx getMotorL() {
        return motorL;
    }
    public DcMotorEx getMotorR() {
        return motorR;
    }
}
