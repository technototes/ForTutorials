package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;

import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.hardware.motor.EncodedMotor;

public class SliderSubsystem implements Subsystem {
    public static class SliderConstant {
        public static double LIFT_UPPER_LIMIT = 2300.0;  // value in encoder ticks, varied by actual hardware design
        public static double LIFT_LOWER_LIMIT = 0.0;   // value in encoder ticks, varied by actual hardware design
        public static double BOTTOM_LEVEL = 0.0;
        public static double MID_LEVEL = 300.0;

        public static PIDCoefficients PID = new PIDCoefficients(0.008, 0, 0.0005);
    }

    public EncodedMotor<DcMotorEx> sliderMotor;

    public PIDFController pidController;

    public SliderSubsystem(EncodedMotor<DcMotorEx> motor) {
        this.sliderMotor = motor;
        this.pidController = new PIDFController(SliderConstant.PID, 0, 0, 0, (x,y)->0.1);
    }

    public void setSliderPosition(double pos){
        pidController.setTargetPosition(Range.clip(pos, SliderConstant.LIFT_LOWER_LIMIT, SliderConstant.LIFT_UPPER_LIMIT));
    }

    @Override
    public void periodic() {
        sliderMotor.setSpeed(Range.clip(-pidController.update(-sliderMotor.get()), -0.9, 0.2));
    }

    public void stop(){
        pidController.reset();
    }

    public Double get() {
        return pidController.getTargetPosition();
    }

    public Double getMotorPosition() {
        return sliderMotor.get();
    }

    public void gotoMiddle(){
        setSliderPosition(SliderConstant.MID_LEVEL);
    }
}
