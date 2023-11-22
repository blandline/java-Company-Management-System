
public class CmdAssignProject extends RecordedCommand{
    private Team t;
    private Project p;
    private ProjectAssignment a;
    

     
    @Override
    public void execute(String[] cmdParts){
        Company company = Company.getInstance();
        p = company.findProject(cmdParts[1]);
        t = company.findTeam(cmdParts[2]);
        a = new ProjectAssignment(t, p);
        company.assignProject(a);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeAssignment(a);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.assignProject(a);
        super.addUndoCommand(this);
    }
}
