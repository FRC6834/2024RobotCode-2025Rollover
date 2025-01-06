package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax kIntakeSystem = new SparkMax(SubsystemConstants.kIntakeSystem, MotorType.kBrushless);

    //runs intake
    public void startMotor(){
        kIntakeSystem.set(-0.35);
    }

    //stops intake
    public void stopMotor(){
        kIntakeSystem.set(0);
    }
}