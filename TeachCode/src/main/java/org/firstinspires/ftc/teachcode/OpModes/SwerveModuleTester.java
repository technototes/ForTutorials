package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teachcode.SwerveModule;

/*
Motors: GoBilda 5202/3/4:
0: leftRearMotor
1: leftFrontMotor
2: rightRearMotor
3: rightFrontMotor
Servos: CR Servos
0: rightRearServo
1: rightFrontServo
4: leftRearServo
5: leftFrontServo
Analog Inputs:
0: rightFrontEncoder
1: rightRearEncoder
2: leftFrontEncoder
3: leftRearEncoder
*/

@TeleOp(name = "Module Test")
public class SwerveModuleTester extends LinearOpMode {
    SwerveModule leftFront;
    SwerveModule rightFront;
    SwerveModule leftRear;
    SwerveModule rightRear;

    public void hwinit() {
        leftFront = new SwerveModule(hardwareMap, "leftFront");
        rightFront = new SwerveModule(hardwareMap, "rightFront");
        leftRear = new SwerveModule(hardwareMap, "leftRear");
        rightRear = new SwerveModule(hardwareMap, "rightRear");
    }

    public void encoderTestOpMode() throws InterruptedException {
        // PIDFCoefficients
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
        telemetry.log().setCapacity(6);
        telemetry.addData("State", "Getting hardware");
        telemetry.update();
        hwinit();
        telemetry.addData("State", "Waiting for start");
        telemetry.update();
        while (!isStarted()) {
            idle();
        }
        ElapsedTime et = new ElapsedTime();
        et.reset();
        while (opModeIsActive()) {
            telemetry.addData("LF", "%f", leftFront.getAngle());
            telemetry.addData("RF", "%f", rightFront.getAngle());
            telemetry.addData("LR", "%f", leftRear.getAngle());
            telemetry.addData("RR", "%f", rightRear.getAngle());
            telemetry.addData("Loop", et.toString());
            et.reset();
            telemetry.update();
        }
    }

    private static double degrees(double val) {
        while (val > 360) {
            val -= 360;
        }
        while (val < 0) {
            val += 360;
        }
        return val;
    }


    public void moduleTestOpMode() throws InterruptedException {
        hwinit();
        while (!isStarted()) idle();
        ElapsedTime et = new ElapsedTime();
        et.reset();
        double nextAngle = 45;
        while (opModeIsActive()) {

            if (gamepad1.triangle) {
                rightFront.setRotatePower(.5);
            } else {
                rightFront.setRotatePower(0);
            }
            if (gamepad1.dpad_up) {
                rightFront.setDrivePower(.3);
            } else {
                rightFront.setDrivePower(0);
            }

            if (gamepad1.square) {
                leftFront.setRotatePower(.5);
            } else {
                leftFront.setRotatePower(0);
            }
            if (gamepad1.dpad_left) {
                leftFront.setDrivePower(.3);
            } else {
                leftFront.setDrivePower(0);
            }

            if (gamepad1.cross) {
                leftRear.setRotatePower(.5);
            } else {
                leftRear.setRotatePower(0);
            }
            if (gamepad1.dpad_down) {
                leftRear.setDrivePower(.3);
            } else {
                leftRear.setDrivePower(0);
            }

            if (gamepad1.circle) {
                rightRear.setRotatePower(.5);
            } else {
                rightRear.setRotatePower(0);
            }
            if (gamepad1.dpad_right) {
                rightRear.setDrivePower(.3);
            } else {
                rightRear.setDrivePower(0);
            }

            telemetry.addData("rightFront", "%f", rightFront.getAngle());
            telemetry.addData("leftFront", "%f", leftFront.getAngle());
            telemetry.addData("rightRear", "%f", rightRear.getAngle());
            telemetry.addData("leftRear", "%f", leftRear.getAngle());
            telemetry.addData("Loop", et.toString());
            et.reset();
            telemetry.update();
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        hwinit();
        while (!isStarted()) idle();
        ElapsedTime et = new ElapsedTime();
        et.reset();
        double nextAngle = 45;
        boolean tri = false, sq = false, cr = false, cir = false;
        boolean up = false;
        while (opModeIsActive()) {
            if (tri != gamepad1.triangle && tri) {
                nextAngle = degrees(nextAngle + 45);
            }
            tri = gamepad1.triangle;
            if (sq != gamepad1.square && sq) {
                nextAngle = degrees(nextAngle - 45);
            }
            sq = gamepad1.square;
            if (cr != gamepad1.cross && cr) {
                nextAngle = degrees(nextAngle + 10);
            }
            cr = gamepad1.cross;
            if (cir != gamepad1.circle && cir) {
                nextAngle = degrees(nextAngle - 10);
            }
            cir = gamepad1.circle;
            if (Math.abs(nextAngle - leftRear.getAngle()) > 5) {
                leftRear.turnToDegrees(nextAngle, telemetry);
            }
            /*
            telemetry.addData("target:", nextAngle);
            telemetry.addData("rightFront", "%f", rightFront.getAngle());
            telemetry.addData("leftFront", "%f", leftFront.getAngle());
            telemetry.addData("rightRear", "%f", rightRear.getAngle());
            telemetry.addData("leftRear", "%f", leftRear.getAngle());
            telemetry.addData("Loop", et.toString());
            et.reset();
            telemetry.update();*/
        }
    }
}
