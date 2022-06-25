package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teachcode.ServoCode;

public class ServoDemo extends OpMode {
    private ServoCode servoCode;
    private Servo servo;

    @Override
    public void init() {
        // Called when INIT button pressed
        this.servoCode = new ServoCode(this.servo);
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
