package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WristController {
    private DcMotor wristMotor;
    private double kP = 0.01;
    private double kI = 0.0;
    private double kD = 0.0005;

    private double integral = 0;
    private double lastError = 0;
    private double targetPosition = 0;

    private ElapsedTime timer = new ElapsedTime();

    public WristController(DcMotor wristMotor) {
        this.wristMotor = wristMotor;
        this.wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.wristMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        timer.reset();
    }

    public void setTargetPosition(double target) {
        this.targetPosition = target;
    }

    public void update() {
        double currentPosition = wristMotor.getCurrentPosition();
        double error = targetPosition - currentPosition;

        double dt = timer.seconds();
        timer.reset();

        integral += error * dt;
        double derivative = (error - lastError) / dt;

        double output = (kP * error) + (kI * integral) + (kD * derivative);
        output = Math.max(-1.0, Math.min(1.0, output));

        wristMotor.setPower(output);
        lastError = error;
    }

    public boolean atTarget(double tolerance) {
        return Math.abs(targetPosition - wristMotor.getCurrentPosition()) < tolerance;
    }
}