public class CmdListleaves implements Command{

    @Override
    public void execute(String[] cmdParts){
        Company c = Company.getInstance();
        
        if(cmdParts.length<2){
            c.listLeaves();
        }
        else{
            c.listLeaves(cmdParts[1]);
        }

        
    }
}