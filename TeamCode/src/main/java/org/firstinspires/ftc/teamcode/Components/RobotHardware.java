package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    public LinearOpMode myOpMode = null;

    public RobotHardware(LinearOpMode opMode) {
        myOpMode = opMode;
    }

    public Servo clawServo;
    public Servo wristServo;

    public DcMotorEx wristMotor;
    public DcMotorEx fLeft;
    public DcMotorEx fRight;
    public DcMotorEx bLeft;
    public DcMotorEx bRight;
    public DcMotorEx lLift;
    public DcMotorEx rLift;

    // Subsystems
    public Mecnum mecnum = new Mecnum();
    public Claw claw = new Claw();
    public Lifts lifts = new Lifts();
    Component[] components = { mecnum, claw, lifts };

    public void init() {
        // Drive motors
        fLeft = myOpMode.hardwareMap.get(DcMotorEx.class, "fLeft");
        fRight = myOpMode.hardwareMap.get(DcMotorEx.class, "fRight");
        bLeft = myOpMode.hardwareMap.get(DcMotorEx.class, "bLeft");
        bRight = myOpMode.hardwareMap.get(DcMotorEx.class, "bRight");

        // Lift motors
        lLift = myOpMode.hardwareMap.get(DcMotorEx.class, "lLift");
        rLift = myOpMode.hardwareMap.get(DcMotorEx.class, "rLift");

        // Wrist motor
        wristMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "wristMotor");
        wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Servos
        clawServo = myOpMode.hardwareMap.get(Servo.class, "clawServo");
        // wristServo = myOpMode.hardwareMap.get(Servo.class, "wristServo"); // Uncomment if you use it

        // Initialize subsystems
        for (Component component : components) {
            component.init(this);
        }

        myOpMode.telemetry.addData("Status", "Initialized");
        myOpMode.telemetry.update();
    }
}
