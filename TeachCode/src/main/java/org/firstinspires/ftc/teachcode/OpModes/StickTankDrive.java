package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teachcode.TankDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "StickTankDrive")

public class StickTankDrive extends OpMode {
    private static final double DEAD_ZONE = 0.1;
    private TankDrive tankDrive;
    private DcMotorEx motorL;
    private DcMotorEx motorR;

    @Override
    public void init() {
        motorL = hardwareMap.get(DcMotorEx.class, "motorL");
        motorR = hardwareMap.get(DcMotorEx.class, "motorR");
        tankDrive = new TankDrive(motorL, motorR);
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > DEAD_ZONE) {
            tankDrive.motorLPower(gamepad1.left_stick_y);
        } else {
            tankDrive.motorLPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) > DEAD_ZONE) {
            tankDrive.motorRPower(-gamepad1.right_stick_y);
        } else {
            tankDrive.motorRPower(0);
        }
    }

    @Override
    public void start() {

    }
}
