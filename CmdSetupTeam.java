public class CmdSetupTeam extends RecordedCommand{
    private Team t;
    
    

     
    @Override
    public void execute(String[] cmdParts){
        
        Company company = Company.getInstance();
        company.createTeam(cmdParts[1], cmdParts[2]);
        t = company.findTeam(cmdParts[1]);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeTeam(t);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.addTeam(t);
        super.addUndoCommand(this);
    }
}
