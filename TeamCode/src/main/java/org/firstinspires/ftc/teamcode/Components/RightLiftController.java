package org.firstinspires.ftc.teamcode.Components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
public class RightLiftController {
        private DcMotor rlift;
        private double kP = 0.01;
        private double kI = 0.0;
        private double kD = 0.0005;

        private double integral = 0;
        private double lastError = 0;
        private double targetPosition = 0;

        private ElapsedTime timer = new ElapsedTime();

        public RightLiftController(DcMotor rlift) {
            this.rlift = rlift;
            this.rlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.rlift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rlift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            timer.reset();
        }

        public void setTargetPosition(double target) {
            this.targetPosition = target;
        }

        public void update() {
            double currentPosition = rlift.getCurrentPosition();
            double error = targetPosition - currentPosition;

            double dt = timer.seconds();
            timer.reset();

            integral += error * dt;
            double derivative = (error - lastError) / dt;

            double output = (kP * error) + (kI * integral) + (kD * derivative);
            output = Math.max(-1.0, Math.min(1.0, output));

            rlift.setPower(output);
            lastError = error;
        }

        public boolean atTarget(double tolerance) {
            return Math.abs(targetPosition - rlift.getCurrentPosition()) < tolerance;
        }
}
