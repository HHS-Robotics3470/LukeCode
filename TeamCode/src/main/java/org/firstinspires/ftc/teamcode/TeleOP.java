package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;
import org.firstinspires.ftc.teamcode.Components.Claw;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Components.RobotHardware;
import org.firstinspires.ftc.teamcode.Components.WristController;

@TeleOp(name="Teleop", group="Linear OpMode")
public class TeleOP extends LinearOpMode {

    private boolean astate = false;
    private boolean xstate = false;
    private boolean wasYPressed = false;
    private boolean wristUp = true;

    private WristController wrist;
    private RobotHardware robot = new RobotHardware(this);

    @Override
    public void runOpMode() {
        robot.init();
        wrist = new WristController(robot.wristMotor); // Use the wrist motor from RobotHardware

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Claw", robot.claw.getClawPos());
            telemetry.addData("Wrist" , robot.claw.getWristPos());
            telemetry.addData("Left Lift", robot.lifts.getlLifts());
            telemetry.addData("Right Lift", robot.lifts.getrLifts());
            robot.mecnum.driveRobot(gamepad1);

            // Claw toggle with A
            if (gamepad1.a && !astate) {
                robot.claw.toggleClaw();
                astate = true;
            } else if (!gamepad1.a && astate) {
                astate = false;
            }

            if (gamepad1.x && !xstate) {
                robot.claw.toggleWrist();
                xstate = true;
            } else if (!gamepad1.x && xstate) {
                xstate = false;
            }

            // Wrist control with Y
            if (gamepad1.y && !wasYPressed) {
                wristUp = !wristUp;
                wrist.setTargetPosition(wristUp ? 0 : -400); // Adjust based on your hardware
            }
            wrist.update();
            wasYPressed = gamepad1.y;

            // Lift control
            if (gamepad1.dpad_up) {
                robot.lifts.raiseLift();
            } else if (gamepad1.dpad_down) {
                robot.lifts.lowerLift();
            } else {
                robot.lifts.sLifts();
            }



            telemetry.update();
        }
    }
}
