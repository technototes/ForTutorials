package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teachcode.SwerveModule;

@TeleOp(name = "Module Tester")
public class SwerveModuleTester extends LinearOpMode {
    SwerveModule frontLeft;
    SwerveModule frontRight;
    SwerveModule rearLeft;
    SwerveModule rearRight;

    public void hwinit() {
        frontLeft = new SwerveModule(hardwareMap, "leftFront");
        frontRight = new SwerveModule(hardwareMap, "leftRear");
        rearLeft = new SwerveModule(hardwareMap, "rightFront");
        rearRight = new SwerveModule(hardwareMap, "rightRear");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        PIDFCoefficients
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
            telemetry.addData("FL", "%d", frontLeft.getAngle());
            telemetry.addData("FR", "%d", frontRight.getAngle());
            telemetry.addData("RL", "%d", rearLeft.getAngle());
            telemetry.addData("RR", "%d", rearRight.getAngle());
            telemetry.addData("Loop", et.toString());
            et.reset();
            telemetry.update();
        }
    }
}
