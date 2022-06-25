package org.firstinspires.ftc.teachcode;

import com.qualcomm.robotcore.hardware.Servo;

public class BasicServoCode {
    private final Servo servo;

    public BasicServoCode(Servo servo) {
        this.servo = servo;
    }

    public void setPosition(double position){
        // Range from 0.0 to 1.0
        this.servo.setPosition(position);
    }

    public void toLeft(){
        this.setPosition(0.0);
    }

    public void toRight(){
        this.setPosition(1.0);
    }

    public void toMiddle(){
        this.setPosition(0.5);
    }

    public double get(){
        return this.servo.getPosition();
    }
}
