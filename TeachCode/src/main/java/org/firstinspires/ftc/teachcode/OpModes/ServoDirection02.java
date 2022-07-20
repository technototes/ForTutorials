package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teachcode.BasicServoCode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "ServoDirection02")
public class ServoDirection02 extends OpMode {
    private BasicServoCode servoCode;
    private Servo servo;

    @Override
    public void init() {
        // Called when INIT button being pressed
        this.servo = hardwareMap.get(Servo.class, "gobilda"); // need to match in the Robot Configuration
        this.servoCode = new BasicServoCode(this.servo);
    }

    @Override
    public void start(){
        // Called when PLAY button being pressed
        this.servoCode.setPosition(0.2);
    }

    @Override
    public void loop() {
        // No usage in this case
    }
}
