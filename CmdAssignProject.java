
public class CmdAssignProject extends RecordedCommand{
    private Team t;
    private Project p;
    private ProjectAssignment a;
    

     
    @Override
    public void execute(String[] cmdParts){
        try {
            if (cmdParts.length<3){
                throw new ExInsufficientArguments();
            } 
            Company company = Company.getInstance();
            p = company.findProject(cmdParts[1]);
            if(p==null){
                throw new ExProjectNotFound();
            }
            t = company.findTeam(cmdParts[2]);
            if(t==null){
                throw new ExTeamNotFound(); 
            }
            a = new ProjectAssignment(t, p);
            company.assignProject(a);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        } catch (ExProjectNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExTeamNotFound e) {
            System.out.println(e.getMessage());
        } 

        
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
