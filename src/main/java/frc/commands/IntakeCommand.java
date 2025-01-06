package frc.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command{
    private final IntakeSubsystem m_IntakeSubsystem;
     public IntakeCommand(IntakeSubsystem subsystem){
        m_IntakeSubsystem = subsystem;
    }
    //called when the command is initialized
    @Override
    public void initialize(){
      m_IntakeSubsystem.startMotor();
    }
    //called every time the command is scheduled
    @Override
    public void execute(){

    }

    @Override
    public void end(boolean interrupted){
      m_IntakeSubsystem.stopMotor();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
