package org.ftc.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Telemetry telemetry;

    public DriveTrain(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double drive, double strafe, double rotate) {
        double frontLeftPower = drive + strafe + rotate;
        double frontRightPower = drive - strafe - rotate;
        double backLeftPower = drive - strafe + rotate;
        double backRightPower = drive + strafe - rotate;

        double maxPower = Math.max(1.0, Math.abs(frontLeftPower));
        maxPower = Math.max(maxPower, Math.abs(frontRightPower));
        maxPower = Math.max(maxPower, Math.abs(backLeftPower));
        maxPower = Math.max(maxPower, Math.abs(backRightPower));

        frontLeft.setPower(frontLeftPower / maxPower);
        frontRight.setPower(frontRightPower / maxPower);
        backLeft.setPower(backLeftPower / maxPower);
        backRight.setPower(backRightPower / maxPower);
    }

    public void printTelemetry() {
        telemetry.addData("frontLeft Motor Position", frontLeft.getCurrentPosition());
        telemetry.addData("frontRight Motor Position", frontRight.getCurrentPosition());
        telemetry.addData("backLeft Motor Position", backLeft.getCurrentPosition());
        telemetry.addData("backRight Motor Position", backRight.getCurrentPosition());
        telemetry.update();
    }

}
