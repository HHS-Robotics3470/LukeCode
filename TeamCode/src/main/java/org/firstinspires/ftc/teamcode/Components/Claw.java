package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Claw implements Component {

    private LinearOpMode myOpMode;
    private Servo clawServo;
    private Servo wristServo;
    private DcMotorEx wristMotor;
    private ElapsedTime wristTimer = new ElapsedTime();

    private final double CLAW_OPEN_POSITION = 0.98;
    private final double CLAW_CLOSE_POSITION = 1.0;
    private final double WRIST_UP_POSITION = 1;
    private final double WRIST_DOWN_POSITION = 0.98;
    private boolean isWristUp = false;

    private boolean isClawOpen = false; // Track claw open/close state

    @Override
    public void init(RobotHardware robotHardware) {
        myOpMode = robotHardware.myOpMode;
        wristMotor = robotHardware.wristMotor;
        clawServo = robotHardware.clawServo;
        wristServo = robotHardware.wristServo;
        wristMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    // Toggle claw open/close state


    public void toggleClaw() {
        isClawOpen = !isClawOpen;  // Toggle claw state

        if (isClawOpen) {
            clawServo.setPosition(CLAW_OPEN_POSITION);
        } else {
            clawServo.setPosition(CLAW_CLOSE_POSITION);
        }
    }

    public void toggleWrist() {
        isWristUp = !isWristUp;  // Toggle claw state

        if (isWristUp) {
            wristServo.setPosition(WRIST_UP_POSITION);
        } else {
            wristServo.setPosition(WRIST_DOWN_POSITION);
        }
    }
    public double getWristPos() {
        return wristServo.getPosition();
    }

    public double getClawPos() {
        return clawServo.getPosition();
    }


    // Toggle wrist position when 'Y' is pressed


}