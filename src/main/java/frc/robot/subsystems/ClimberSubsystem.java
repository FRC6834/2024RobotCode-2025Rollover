package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

public class ClimberSubsystem extends SubsystemBase {
    
    //Climber Variables
    private final SparkMax climberLeft = new SparkMax(SubsystemConstants.kLeftClimber, MotorType.kBrushless);
    private final SparkMax climberRight = new SparkMax(SubsystemConstants.kRightClimber, MotorType.kBrushless);

    //Left Climber goes Up
    public void climberLeftUp(){
        climberLeft.set(-.75);
    }
    //Left Climber goes Down
    public void climberLeftDown(){
        climberLeft.set(.75);
    }

    //Right Climber goes Up
    public void climberRightUp(){
        climberRight.set(.75);
    }

    //Right Climber goes DOwn
    public void climberRightDown(){
        climberRight.set(-.75);
    }

    //Right Climber no go
    public void stopRightMotor(){
        climberRight.set(0);
    }

    //Left climber no go
    public void stopLeftMotor(){
        climberLeft.set(0);
    }
}