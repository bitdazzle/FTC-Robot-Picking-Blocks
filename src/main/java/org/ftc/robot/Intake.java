package org.ftc.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    /*
     * Variables to store the speed the intake servo should be set at to intake, and
     * deposit game elements.
     */
    private static final double INTAKE_COLLECT = 0;
    private static final double INTAKE_OFF = 0.5;
    private static final double INTAKE_DEPOSIT = 1;

    /*
     * Variables to store the positions that the wrist should be set to when folding
     * in, or folding out.
     */
    private static final double WRIST_FOLDED_IN = 0;
    private static final double WRIST_FOLDED_OUT = 0.25;

    private Telemetry telemetry;
    private Servo intake = null; // the active intake servo
    private Servo wrist = null; // the wrist servo

    public Intake(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        /* Define and initialize servos. */
        intake = hardwareMap.get(Servo.class, "intake");
        wrist = hardwareMap.get(Servo.class, "wrist");

        /* Make sure that the intake is off, and the wrist is folded in. */
        intake.setPosition(INTAKE_OFF);
        wrist.setPosition(WRIST_FOLDED_IN);
    }

    public void positionIntakeForSample() {
        wrist.setPosition(WRIST_FOLDED_OUT);
    }

    public void pickSample() {
        intake.setPosition(INTAKE_COLLECT);
    }

    public void stopIntake() {
        intake.setPosition(INTAKE_OFF);
    }

    public void depositSample() {
        intake.setPosition(INTAKE_DEPOSIT);
    }

    public void retractIntake() {
        intake.setPosition(INTAKE_OFF);
        wrist.setPosition(WRIST_FOLDED_IN);
    }

    public void printTelemetry() {
        telemetry.addData("Wrist Servo Motor Position", wrist.getPosition());
        telemetry.addData("org.ftc.robot.Intake Servo Motor Position", intake.getPosition());
        telemetry.update();
    }
}
