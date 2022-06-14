package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teachcode.SwerveController;

@TeleOp(name = "Test Swerve")
public class TestSwerveModule extends LinearOpMode {
    private static double ROT_DEAD_ZONE = 0.1;
    private static double POS_DEAD_ZONE = 0.1;
    private static double INV_ROOT_2 = 1.0 / Math.sqrt(2.0);

    private SwerveController sm;
    private BNO055IMU imu;
    private CRServo srvo;
    private Servo s;
    private ElapsedTime runtime = new ElapsedTime();

    private static double inRange(double low, double high, double val) {
        // Dumb & slow, but easy :D
        while (val < low) {
            val += (high - low);
        }
        while (val >= high) {
            val -= (high - low);
        }
        return val;
    }

    private static double clamp(double low, double high, double val) {
        if (val < low) return low;
        if (val > high) return high;
        return val;
    }


    private void hwinit() {
        DcMotorEx m = hardwareMap.get(DcMotorEx.class, "test1");
        s = hardwareMap.get(Servo.class, "testServo");
        // srvo = hardwareMap.get(CRServo.class, "testCR");
        // sm = new SwerveModule(m, s);
        // imu = hardwareMap.get(BNO055IMU.class, "imu");
    }

    // Notes: Gamepad stick orientiation is North -, West -, South +, East +
    @Override
    public void runOpMode() {
        hwinit();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        telemetry.addData("Status", "Started");
        telemetry.update();
        while (opModeIsActive()) {
            double rx = gamepad2.right_stick_x;
            double ry = gamepad2.right_stick_y;
            double y = gamepad2.left_stick_y;
            double x = gamepad2.left_stick_x;
            double rx1 = gamepad1.right_stick_x;
            double ry1 = gamepad1.right_stick_y;
            double y1 = gamepad1.left_stick_y;
            double x1 = gamepad1.left_stick_x;
            // For a normal servo, the position is even across about 270 degrees (+/- 135)
            // from position 0 to 1.0
            s.setPosition((1.0 + rx + rx1) / 2.0);
            double pos = s.getPosition(); // This is just 'get power' cuz CRServo!
            telemetry.addData("Left 1", String.format("%f, %f", rx1, ry1));
            telemetry.addData("Right 1", String.format("%f, %f", x1, y1));
            telemetry.addData("Left 2", String.format("%f, %f", rx, ry));
            telemetry.addData("Right 2", String.format("%f, %f", x, y));
            telemetry.addData("Servo:", pos);
            /*
            if (Math.abs(rx) >= ROT_DEAD_ZONE) {
                // This lets you rotate a little slower by default
                // If you want to rotate faster, crank the y axis (either direction)
                double magnitude = Math.sqrt(rx * rx + ry * ry) * INV_ROOT_2;
                double sign = (rx < 0) ? -1.0 : 1.0;
                // Square the value to scale non-linearly
                double degrees = 180 * magnitude * magnitude * sign;
                sm.setDegrees(inRange(-180, 180, degrees));
                telemetry.addData("Rotating", degrees);
            } else if (x >= POS_DEAD_ZONE || y >= POS_DEAD_ZONE) {
                // I have no idea if the angle to take the IMU position into account
                // for a driver-relative position is correct. The math should be *easy* but
                // it really does need to be *correct* :)
                float pos = imu.getAngularOrientation().toAngleUnit(AngleUnit.RADIANS).firstAngle;
                double pow = clamp(0, 1, Math.sqrt(y * y + x * x));
                sm.setPower(pow * ((y < 0) ? -1 : 1));
                telemetry.addData("Moving",  pow);
            } else {
                sm.setPower(0);
            }
             */
            telemetry.addData("Elapsed Time", runtime.toString());
            telemetry.update();
        }
    }
}
