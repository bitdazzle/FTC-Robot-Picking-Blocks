package org.ftc.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    private static int SHOULDER_BASKET_EXPAND_POSITION = 550;
    private static int SHOULDER_BASKET_RETRACT_POSITION = 5;
    private static int ELBOW_BASKET_EXPAND_POSITION = 350;
    private static int ELBOW_BASKET_RETRACT_POSITION = 1;

    private static int SHOULDER_SAMPLE_PICK_POSITION = 224;
    private static int ELBOW_SAMPLE_PICK_POSITION = 377;

    private static float SHOULDER_EXPAND_POWER = 0.5f;
    private static float ELBOW_EXPAND_POWER = 0.5f;
    private static float SHOULDER_RETRACT_POWER = 0.25f;
    private static float ELBOW_RETRACT_POWER = 0.25f;

    private DcMotor elbow;
    private DcMotor shoulder;
    private Telemetry telemetry;

    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        elbow = hardwareMap.get(DcMotor.class, "elbow");
        shoulder = hardwareMap.get(DcMotor.class, "shoulder");

        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setDirection(DcMotor.Direction.FORWARD);

        shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulder.setDirection(DcMotor.Direction.FORWARD);
    }

    public void positionArmForSample() {
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setTargetPosition(SHOULDER_SAMPLE_PICK_POSITION);
        shoulder.setPower(SHOULDER_EXPAND_POWER);

        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setTargetPosition(ELBOW_SAMPLE_PICK_POSITION);
        elbow.setPower(ELBOW_EXPAND_POWER);
    }

    public void moveElbowUp() {
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setTargetPosition(shoulder.getCurrentPosition() + 10);
        elbow.setPower(ELBOW_EXPAND_POWER);
    }

    public void moveElbowDown() {
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setTargetPosition(shoulder.getCurrentPosition() - 10);
        elbow.setPower(ELBOW_EXPAND_POWER);
    }

    public void positionArmForFirstBasket() {
        shoulder.setTargetPosition(SHOULDER_BASKET_EXPAND_POSITION);
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setPower(SHOULDER_EXPAND_POWER);

        elbow.setTargetPosition(ELBOW_BASKET_EXPAND_POSITION);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setPower(ELBOW_EXPAND_POWER);
    }

    public void retractArm() {
        shoulder.setTargetPosition(SHOULDER_BASKET_RETRACT_POSITION);
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setPower(SHOULDER_RETRACT_POWER);

        elbow.setTargetPosition(ELBOW_BASKET_RETRACT_POSITION);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setPower(ELBOW_RETRACT_POWER);
    }

    // Stop motor if not busy
    public void restArm() {
        elbow.setPower(0);
        shoulder.setPower(0);
    }

    public void printTelemetry() {
        telemetry.addData("Elbow Motor Position", elbow.getCurrentPosition());
        telemetry.addData("Shoulder Motor Position", shoulder.getCurrentPosition());
        telemetry.update();
    }
}
