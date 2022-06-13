package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class SwerveModule {
    private DcMotorEx m;
    private Servo s;

    public SwerveModule(DcMotorEx motor, Servo servo) {
        m = motor;
        s = servo;
    }

    public void setDegrees(double angle) {
        s.setPosition(angle / 360.0);
    }

    public void setRadians(double angle) {
        s.setPosition(angle / Math.PI);
    }

    public void setPower(double power) {
        m.setPower(power);
    }

    public void setControlRadians(double power, double angle) {
        setPower(power);
        setRadians(angle);
    }

    public void setControlDegrees(double power, double angle) {
        setPower(power);
        setDegrees(angle);
    }
}
