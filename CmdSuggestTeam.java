public class CmdSuggestTeam implements Command{
    private Project p;
    
    

     
    @Override
    public void execute(String[] cmdParts){
        
        
       
        Company company = Company.getInstance();
        p = company.findProject(cmdParts[1]);
        company.suggestTeam(p);
        
        

        
    }

    

   
}
