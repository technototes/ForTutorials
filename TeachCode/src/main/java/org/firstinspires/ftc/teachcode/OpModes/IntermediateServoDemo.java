package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teachcode.BasicServoCode;
import org.firstinspires.ftc.teachcode.util.MathHelper;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "IntermediateServoDemo")
public class IntermediateServoDemo extends OpMode {
    private BasicServoCode servoCode;
    private Servo servo;
    private Gamepad gamepad;
    private double inputtedPosition = 0.0;

    @Override
    public void init() {
        // Called when INIT button being pressed
        this.servo = hardwareMap.get(Servo.class, "gobilda"); // need to match in the Robot Configuration
        this.servoCode = new BasicServoCode(this.servo);
        this.gamepad = gamepad1;  // it's something built-in in the OpMode class
    }

    @Override
    public void start(){
        // Called when PLAY button being pressed
        // this.servoCode.toLeft();
        this.servoCode.toMiddle();
    }

    @Override
    public void loop() {
        // Called every time the robot runs
        // Might be a bad solution since this will set the servo to the same position multiple times
        // in the duration of the button being set the position multiple times
        // UPDATE: its fine to
        if (this.gamepad.dpad_left) {
            this.servoCode.toLeft();
        } else if (this.gamepad.dpad_right) {
            this.servoCode.toRight();
        } else if (this.gamepad.dpad_up) {
            this.servoCode.toMiddle();
        } else if (this.gamepad.left_bumper){
            this.inputtedPosition = MathHelper.clamp(this.inputtedPosition - 0.01, 0.0, 1.0);
            this.servoCode.setPosition(this.inputtedPosition);
        } else if (this.gamepad.right_bumper){
            this.inputtedPosition = MathHelper.clamp(this.inputtedPosition + 0.01, 0.0, 1.0);
            this.servoCode.setPosition(this.inputtedPosition);
        }
    }
}
