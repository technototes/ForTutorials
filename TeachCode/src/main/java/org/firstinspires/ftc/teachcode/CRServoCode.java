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
        /*
         * param power: Range from 0.0 to 1.0
         */
        this.servo.setPower(power);
    }

    public DcMotorSimple.Direction getDirection() {
        return this.servo.getDirection();
    }

    public void setDirection(DcMotorSimple.Direction direction) {
        this.servo.setDirection(direction);
    }

    public void invertDirection() {
        this.servo.setDirection(this.servo.getDirection() == DcMotorSimple.Direction.FORWARD ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }
}
