package frc.robot.subsystems.lift;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Claw extends SubsystemBase {
    
    private final WPI_TalonFX mMotor;

    public Claw() {

        mMotor = new WPI_TalonFX(Constants.CAN.kClaw);

    }

}
