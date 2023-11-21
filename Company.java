import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Company {
    
    private ArrayList<Project> allProjects;
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<ProjectAssignment> allAssignments;

    private static Company instance = new Company() ;

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
        allAssignments = new ArrayList<>();

    }

    public static Company getInstance() {
        return instance;
    }

    public void listTeams() {
        Team.list( allTeams );
    }

    public Employee createEmployee( String n,int al ) // See how it is called in main()
    {
        Employee e = new Employee( n,al );
        allEmployees.add( e );
        Collections.sort( allEmployees ); //allEmployees
        return e ;
    }
    public Project createProject(String pn,String d,int t){
        Project p = new Project(pn, d, t);
        allProjects.add(p);
        //Collections.sort( allEmployees ); //allEmployees       #incase needed to sort 
        return p;
    }

    public Team createTeam( String tn,String leaderName ) // See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees, leaderName );
        Team t = new Team( tn,e );
        allTeams.add( t );
        Collections.sort( allTeams ); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    public void removeTeam(Team a){
        allTeams.remove(a);
    }

    public void removeEmployee(Employee e){
        allEmployees.remove(e);
    }
    public void addTeam(Team t){
        allTeams.add(t);
    }

    public void addEmployee(Employee e){
        allEmployees.add(e);
    }
    public void listEmployees(){
        for(Employee e: allEmployees){
            System.out.println(e);
        }
    }
     public void listProjects(){
        for(Project p: allProjects){
            String team = "--";
            for(int i =0;i<allAssignments.size();i++){       // make a func to check if it is in assignments and if it is then print that
                if(allAssignments.get(i).getProject()==p){
                    team = allAssignments.get(i).getTeamName();
                }    
            
            }
            System.out.println(p+team);
        }
    }


   
    public Team findTeam(String n){
        for(Team t: allTeams){
            if(t.getTeamName().equals(n)){
                return t;
            }
        }
        return null;
    }

    public Project findProject(String n){
        for(Project p: allProjects){
            if(p.getProjName().equals(n)){
                return p;
            }
        }
        return null;
    }

    public Employee findEmployee(String n){
        for(Employee e: allEmployees){
            if(e.getName().equals(n)){
                return e;
            }
        }
        return null;
    }

    public String getProjTeam(Object n) {
        for(Team t: allTeams){
            if(t.getProject().getProjName().equals(n)){
                return t.getTeamName();
            }
        }
        return null;
    }

    public void assignProject(ProjectAssignment a) {
        allAssignments.add(a);
    }

}
