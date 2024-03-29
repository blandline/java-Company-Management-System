

    
public class CmdCreateProject extends RecordedCommand{
    private Project p;
    private String projName;
    private int projTime;
    

     
    @Override
    public void execute(String[] cmdParts){
        try {
            if (cmdParts.length<4){
                throw new ExInsufficientArguments();
            } 
            Company company = Company.getInstance();
            projName = cmdParts[1];
            projTime = Integer.parseInt(cmdParts[3]);
            company.createProject(cmdParts[1], cmdParts[2],projTime);
            p = company.findProject(cmdParts[1]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        
        } catch (ExProjectAlreadyExists e) {
            
            System.out.println(e.getMessage());
        } catch (ExInsufficientArguments e) {
            
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Wrong number format for project duration!");
        }

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeProject(p);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.addProject(p);
        super.addUndoCommand(this);
    }
}


