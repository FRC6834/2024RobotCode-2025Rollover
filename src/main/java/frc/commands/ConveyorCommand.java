package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyorCommand extends Command {
    private final ConveyorSubsystem m_ConveyorSubsystem;
     public ConveyorCommand(ConveyorSubsystem subsystem){
        m_ConveyorSubsystem = subsystem;
    }
    //called when the command is initialized
    @Override
    public void initialize(){
      m_ConveyorSubsystem.forwardMotor();
    }
    //called every time the command is scheduled
    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
      m_ConveyorSubsystem.stopMotor();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
