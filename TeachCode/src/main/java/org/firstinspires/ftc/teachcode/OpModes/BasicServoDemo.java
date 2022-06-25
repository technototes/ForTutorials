package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teachcode.BasicServoCode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "BasicSensorDemo")

public class BasicServoDemo extends OpMode {
    private BasicServoCode servoCode;
    private Servo servo;

    @Override
    public void init() {
        // Called when INIT button pressed
        this.servo = hardwareMap.get(Servo.class, "servo"); // need to match in the Robot Configuration
        this.servoCode = new BasicServoCode(this.servo);
    }

    @Override
    public void start(){
        // Called when PLAY button pressed
        this.servoCode.toLeft();
    }

    @Override
    public void loop() {

    }
}
