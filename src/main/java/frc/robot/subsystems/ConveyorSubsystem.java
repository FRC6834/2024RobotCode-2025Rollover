package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SubsystemConstants;

public class ConveyorSubsystem extends SubsystemBase {

    private final SparkMax conveyorMotor = new SparkMax(SubsystemConstants.kConveyorBelt, MotorType.kBrushless);

    //Conveyor goes forward - 30% speed
    public void forwardMotor(){
        conveyorMotor.set(-.6);
    }

    //Conveyor goes forward - half normal speed
    public void halfForwardMotor(){
        conveyorMotor.set(-.15);
    }

    //Covneyor moves backwards - 30% speed
    public void backwardsMotor(){
        conveyorMotor.set(.15);
    }

    //Covneyor stops
    public void stopMotor(){
        conveyorMotor.set(0);
    }   
}