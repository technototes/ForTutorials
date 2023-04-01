package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class MeasureMotors extends LinearOpMode {
    DcMotorEx leftMotor;
    DcMotorEx rightMotor;

    double leftMotorZero = 0;
    double rightMotorZero = 0;

    void setMotorsPower(double p){
        leftMotor.setPower(p);
        rightMotor.setPower(p);
    }

    double getLeftPos() {
        return leftMotor.getCurrentPosition() - leftMotorZero;
    }

    double getRightPos() {
        return rightMotor.getCurrentPosition() - rightMotorZero;
    }

    @Override
    public void runOpMode() throws InterruptedException {
        leftMotor = hardwareMap.get(DcMotorEx.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");

        leftMotorZero = leftMotor.getCurrentPosition();
        rightMotorZero = rightMotor.getCurrentPosition();

        waitForStart();

        while (opModeIsActive()) {
            if (getLeftPos() < 1500) {
                leftMotor.setPower(1);
            }
            else {
                leftMotor.setPower(0);
            }

            if (getRightPos() < 1500) {
                rightMotor.setPower(1);
            }
            else {
                rightMotor.setPower(0);
            }

        }

        // DO NOT forget to stop
        setMotorsPower(0);
    }
}
