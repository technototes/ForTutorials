package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teachcode.TankDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "StickTankDrive")

public class StickTankDrive extends OpMode {

    private TankDrive tankDrive;
    private DcMotorEx motorL;
    private DcMotorEx motorR;
    private Gamepad gamerPad;

    @Override
    public void init() {
    tankDrive = new TankDrive(motorL, motorR);
    }

    @Override
    public void loop() {
        if (gamerPad.left_stick_y != 0) {
            tankDrive.motorLPower(gamerPad.left_stick_y);
        }
        if (gamerPad.right_stick_y != 0) {
            tankDrive.motorRPower(gamerPad.right_stick_y);
        }
    }

    @Override
    public void start() {

    }
}
