
public class CmdHire extends RecordedCommand{
    private Employee e;
    private int annualLeaves;
    

     
    @Override
    public void execute(String[] cmdParts){
    
        try {
            if (cmdParts.length<3){
                throw new ExInsufficientArguments();
            }
            Company company = Company.getInstance();
            annualLeaves = Integer.parseInt(cmdParts[2]);
 
            company.createEmployee(cmdParts[1], annualLeaves);
            e = company.findEmployee(cmdParts[1]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExEmployeeExists e) {
            System.out.println(e.getMessage());
        } catch (ExInsufficientArguments e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("Wrong number format for annual leaves!");
        }
        

        
    }

    @Override
    public void undoMe(){
        Company company = Company.getInstance();
        company.removeEmployee(e);
        super.addRedoCommand(this); 
    }

    @Override
    public void redoMe(){
        Company company = Company.getInstance();
        company.addEmployee(e);
        super.addUndoCommand(this);
    }
}
