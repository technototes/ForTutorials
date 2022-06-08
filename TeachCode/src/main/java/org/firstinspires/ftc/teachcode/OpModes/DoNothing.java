package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Simple do-nothing tele-op (Linear op mode)")
// @Disabled
class DoNothing extends LinearOpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Don't do anything in here :D
            telemetry.addData("Elapsed Time", runtime.toString());
        }
    }
}