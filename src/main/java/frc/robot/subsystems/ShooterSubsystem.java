package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkMax leftMotor = new SparkMax(SubsystemConstants.kLeftShooter, MotorType.kBrushless);
    private final SparkMax rightMotor = new SparkMax(SubsystemConstants.kRightShooter, MotorType.kBrushless);

    //Shooter goes - 100%
    //Right is reversed on purpose
    public void startMotor(){
        leftMotor.set(1);
        rightMotor.set(-1);
    }

    //Stops shooter
    public void stopMotor(){
        leftMotor.set(0);
        rightMotor.set(0);
    }
}