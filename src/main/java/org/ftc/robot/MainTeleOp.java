package org.ftc.robot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.lamedemo.DriveTrain;
import org.firstinspires.ftc.teamcode.lamedemo.Arm;

@TeleOp(name = "LameDuckDemo", group = "LameDuckDemo")
public class MainTeleOp extends LinearOpMode {

    private DriveTrain drivetrain;
    private Arm arm;
    private Intake intake;
    private boolean armInDepositPosition = false;

    @Override
    public void runOpMode() {
        // Initialize the drivetrain and org.ftc.robot.Arm
        drivetrain = new DriveTrain(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        intake = new Intake(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            // Read gamepad inputs
            double drive = -gamepad1.left_stick_y;
            double strafe = gamepad1.left_stick_x;
            double rotate = gamepad1.right_stick_x;

            // Drive the robot
            drivetrain.drive(drive, strafe, rotate);

            //position arm and intake for sample
            if (gamepad1.a) {
                intake.positionIntakeForSample();
                arm.positionArmForSample();
                armInDepositPosition = false;
            } //position arm for first basket
            else if (gamepad1.b) {
                arm.positionArmForFirstBasket();
                armInDepositPosition = true;
                //pick sample
            } else if (gamepad1.right_trigger > 0.4) {
                intake.pickSample();
                armInDepositPosition = false;
                //deposit sample in basket
            } else if (gamepad1.left_trigger > 0.4 && armInDepositPosition) {
                intake.depositSample();
                //move elbo up
            } else if (gamepad1.dpad_up) {
                arm.moveElbowUp();
                armInDepositPosition = false;
                //move elbo down
            } else if (gamepad1.dpad_down) {
                arm.moveElbowDown();
                armInDepositPosition = false;
                //retract the whole arm
            } else if (gamepad1.x) {
                intake.retractIntake();
                arm.retractArm();
                armInDepositPosition = false;
            } else { //stop running intake if not necessary to run
                intake.stopIntake();
                armInDepositPosition = false;
            }

            /*
             * else if (!leftMotor.isBusy()) {
             * arm.rest();
             * }
             */
            telemetry.addData("Is robot arm in deposit position", armInDepositPosition);
            // drivetrain.printTelemetry();
            arm.printTelemetry();
            telemetry.update();
        }
    }
}
