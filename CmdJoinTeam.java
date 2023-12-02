public class CmdJoinTeam extends RecordedCommand{
    private Team t;
    private Employee e;
    
    

     
    @Override
    public void execute(String[] cmdParts){
        
        
        try {
            if (cmdParts.length<3){
                throw new ExInsufficientArguments();
            } 
            Company company = Company.getInstance();
            t = company.findTeam(cmdParts[2]);
            e = company.findEmployee(cmdParts[1]);
            if(e==null){
                throw new ExEmployeeNotFound();
            }
            if(t==null){
                throw new ExTeamNotFound();
            }

            t.addToTeam(e);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeNotFound e) {
           System.out.println(e.getMessage());
        } catch (ExTeamNotFound e) {
           System.out.println(e.getMessage());
        } catch (ExJoinedAnotherTeam e) {
            System.out.println(e.getMessage());
        } 
        

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeFromTeam(t,e);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        try {
            t.addToTeam(e);
        } catch (ExJoinedAnotherTeam e) {
            System.out.println(e.getMessage());
        }
        super.addUndoCommand(this);
    }
}
