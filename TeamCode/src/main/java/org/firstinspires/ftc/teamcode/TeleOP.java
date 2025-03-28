package org.firstinspires.ftc.teamcode;

/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Components.RobotHardware;
import org.firstinspires.ftc.teamcode.Components.Claw;

@TeleOp(name="Teleop", group="Linear OpMode")
public class TeleOP extends LinearOpMode {

    private boolean astate = false;


    RobotHardware robot = new RobotHardware(this);

    @Override
    public void runOpMode() {
        RobotHardware robot = new RobotHardware(this);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses START)



        waitForStart();
        robot.init();


        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Claw", robot.claw.getClawPos());
            telemetry.addData("Wrist", robot.claw.getWristPos());
            telemetry.addData("Left Lift", robot.lifts.getlLifts());
            telemetry.addData("Right Lift", robot.lifts.getrLifts());
            robot.mecnum.driveRobot(gamepad1);

            if (gamepad1.a && !astate)
            {
                robot.claw.toggleClaw();
                astate = true;
            }
            else if (!gamepad1.a && astate) {
                astate = false;
            }



            //old toggle code
           // if (gamepad1.a){
             //   robot.claw.toggleClaw();
            //}
         //   if (gamepad1.a) {
           //     robot.claw.clawOpen();
            //}
           // if(gamepad1.b){
             //   robot.claw.clawClose();
            //}





            if (gamepad1.y) {
                robot.claw.toggleWrist();
            }





            if (gamepad1.dpad_up) {
                robot.lifts.raiseLift();
            } else if (gamepad1.dpad_down) {
                robot.lifts.lowerLift();
            }else
                robot.lifts.sLifts();
            telemetry.update();
        }
    }
}
