package org.firstinspires.ftc.teachcode.OpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teachcode.SwerveModule;

@TeleOp(name = "Test Swerve")
public class TestSwerveModule extends LinearOpMode {
    private static double ROT_DEAD_ZONE = 0.1;
    private static double POS_DEAD_ZONE = 0.1;
    private static double INV_ROOT_2 = 1.0 / Math.sqrt(2.0);

    SwerveModule sm;
    BNO055IMU imu;

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
        Servo s = hardwareMap.get(Servo.class, "testServo");
        sm = new SwerveModule(m, s);
        imu = hardwareMap.get(BNO055IMU.class, "imu");
    }

    @Override
    public void runOpMode() {
        hwinit();
        waitForStart();
        while (opModeIsActive()) {
            double rx = gamepad2.right_stick_x;
            double ry = gamepad2.right_stick_y;
            double y = gamepad2.left_stick_y;
            double x = gamepad2.left_stick_x;
            if (Math.abs(rx) >= ROT_DEAD_ZONE) {
                // This lets you rotate a little slower by default
                // If you want to rotate faster, crank the y axis (either direction)
                double magnitude = Math.sqrt(rx * rx + ry * ry) * INV_ROOT_2;
                double sign = (rx < 0) ? -1.0 : 1.0;
                // Square the value to scale non-linearly
                sm.setDegrees(inRange(-180, 180, 360 * magnitude * magnitude * sign));
            } else if (x >= POS_DEAD_ZONE || y >= POS_DEAD_ZONE) {
                // I have no idea if the angle to take the IMU position into account
                // for a driver-relative position is correct. The math should be *easy* but
                // it really does need to be *correct* :)
                float pos = imu.getAngularOrientation().toAngleUnit(AngleUnit.RADIANS).firstAngle;
                double rad = inRange(-Math.PI, Math.PI, Math.atan2(y, x) - pos);
                double pow = clamp(-1, 1, Math.sqrt(y * y + x * x));
                sm.setPower(pow);
                sm.setRadians(rad);
            } else {
                sm.setPower(0);
            }
        }
    }
}
