package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SwerveModule {
    private DcMotorEx motor;
    private CRServo servo;
    private AnalogInput sensor;

    /* Stuff for the encoder: I should encapsulate this stuff */
    private ElapsedTime lastRead;
    private int lastAngle;
    private static final int range = 15;
    private static final double msRange = 50.0;
    public int getAngle() {
        double thing = sensor.getVoltage();
        int angle = (int) Math.round(360 * thing / 2.321) % 360;
        if ((lastAngle < 3 || lastAngle > 357) && (angle > 3 + range && angle < 357 - range)
                && lastRead.milliseconds() < msRange) {
            angle = lastAngle;
        } else {
            lastAngle = angle;
        }
        lastRead.reset();
        return angle;
    }
    /* End stuff for encoder */

    public void setRotatePower(double d) {
        servo.setPower(d);
    }

    public void setDrivePower(double d) {
        motor.setPower(d);
    }

    public SwerveModule(HardwareMap hw, String name) {
        motor = hw.get(DcMotorEx.class, name + "Motor");
        servo = hw.get(CRServo.class, name + "Servo");
        sensor = hw.get(AnalogInput.class, name + "Encoder");
        lastRead = new ElapsedTime();
    }

    public void manualDriveDegrees(double power, int angle) {
        // double servoError = goToServo(angle);
    }

    public void manualDriveRadians(double power, double angle) {
        manualDriveDegrees(power, (int) Math.round(angle * 180 / Math.PI));
    }
}
