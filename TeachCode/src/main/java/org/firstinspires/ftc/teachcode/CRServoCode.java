package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class CRServoCode {
    private final CRServo servo;

    public CRServoCode(CRServo servo) {
        this.servo = servo;
    }

    public double getPower() {
        return this.servo.getPower();
    }

    public void setPower(double power) {
        this.servo.setPower(power);
    }

    public DcMotorSimple.Direction getDirection() {
        return this.servo.getDirection();
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        this.servo.setDirection(direction);
    }

    public void invertDirection() {
        this.setDirection(this.servo.getDirection() == DcMotorSimple.Direction.FORWARD ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

    public void stop() {
        this.setPower(0);
    }

    public void setMaxPositiveSpeed() {
        this.setPower(1);
    }

    public void setMaxNegativeSpeed() {
        this.setPower(-1);
    }
}
