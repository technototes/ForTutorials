package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.technototes.library.hardware.motor.EncodedMotor;

import org.firstinspires.ftc.teachcode.SliderSubsystem;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "BasicSliderDemo")
public class BasicSliderDemo extends OpMode {
    private SliderSubsystem sliderSubsystem;
    public EncodedMotor<DcMotorEx> motor;
    private Gamepad gamepad;

    @Override
    public void init() {
        this.motor = new EncodedMotor<DcMotorEx>(hardwareMap.get(DcMotorEx.class, "slider_motor"));
        this.sliderSubsystem = new SliderSubsystem(this.motor);
        this.gamepad = gamepad1;  // it's something built-in in the OpMode class
    }

    @Override
    public void loop() {
        if (this.gamepad.dpad_left) {
            this.sliderSubsystem.gotoMiddle();
        } else if (this.gamepad.dpad_right) {

        } else if (this.gamepad.dpad_down) {

        }

        telemetry.addData("CurrentMotorRead", this.sliderSubsystem.getMotorPosition());
    }
}
