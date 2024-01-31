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

    public Employee createEmployee( String n,int al ) throws ExEmployeeExists // See how it is called in main()
    {
        Employee e = new Employee( n,al );
        if(findEmployee(e.getName())!=null){
            throw new ExEmployeeExists();
        }
        allEmployees.add( e );
        Collections.sort( allEmployees ); //allEmployees
        return e ;
    }
    public void addProject(Project p){
        allProjects.add(p);
    }
    public Project createProject(String pn,String d,int t) throws ExProjectAlreadyExists{
        Project p = new Project(pn, d, t);
        if(findProject(p.getProjName())!=null){
            throw new ExProjectAlreadyExists();
        }
        allProjects.add(p);
        Collections.sort( allProjects );       
        return p;
    }

    public boolean joinedAnotherTeam(Employee e){
        for (Team t : allTeams) {
            if(t.findMember(e)!=null||t.getLeaderName().equals(e.getName())){
                return true;
            }
        }
        return false;
    }

    public Team createTeam( String tn,String leaderName ) throws ExEmployeeNotFound,ExJoinedAnotherTeam,ExTeamAlreadyExists // See how it is called in main()
    {
        Employee e = Employee.searchEmployee(allEmployees, leaderName );
        if(e==null){
            throw new ExEmployeeNotFound();
        }
        if(findTeamFromLeader(e.getName())!=null||joinedAnotherTeam(e)){
            String eString = String.format("%s has already joined another team: %s",e.getName(),findTeamFromLeader(e.getName()).getTeamName());
            throw new ExJoinedAnotherTeam(eString);
        }
        if(findTeam(tn)!=null){
            
            throw new ExTeamAlreadyExists();
        }
        Team t = new Team( tn,e );
        allTeams.add( t );
        Collections.sort( allTeams ); //allTeams
        return t; //Why return?  Ans: Later you'll find it useful for undoable comments.
    }

    // public void takeLeaves(String n,String s,String e){
    //     Leave l
    // }

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
        System.out.printf("%-9s%-13s%-13s%-13s\n","Project", "Start Day", "End Day", "Team");
        for(Project p: allProjects){
            String team = "--";
            for(int i =0;i<allAssignments.size();i++){       // make a func to check if it is in assignments and if it is then print that
                if(allAssignments.get(i).getProject()==p){
                    team = allAssignments.get(i).getTeamName()+" ("+allAssignments.get(i).getTeam().getLeaderName()+")";
                }    
            
            }
            System.out.printf(p+"%-13s%n",team);
        }
    }


   
    public Team findTeam(String n){//remember to add throws
        for(Team t: allTeams){
            if(t.getTeamName().equals(n)){
                return t;
            }
        }
        return null;
    }

    public Team findTeamFromLeader(String n){
        for(Team t: allTeams){
            if(t.getLeaderName().equals(n)){
                return t;
            }
        }
        return null;
    }

    public Team findTeamFromMember(Employee e){
        if(findTeamFromLeader(e.getName())!=null){
            return findTeamFromLeader(e.getName());
        }
        else{
            for(Team t:allTeams){
                if(t.findMember(e)!=null){
                    return t;
                }
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

    // public String getProjTeam(Object n) {
    //     for(Team t: allTeams){
    //         if(t.getProject().getProjName().equals(n)){
    //             return t.getTeamName();
    //         }
    //     }
    //     return null;
    // }                    //adjust accordingly later as per requirment

    public void assignProject(ProjectAssignment a) {
        allAssignments.add(a);
    }

    public void removeAssignment(ProjectAssignment a) {
        allAssignments.remove(a);
    }

    public void removeProject(Project p) {
        allProjects.remove(p);
    }

    public void takeLeaves(String employeeName, Leave l)throws ExInsufficientLeaves,ExLeavesOverLapping,ExFinalStageOverlap{
        try {
            Employee employee = findEmployee(employeeName);
            if(employee.getLeavesLeft()<l.getDifference()){
                throw new ExInsufficientLeaves(employee.getLeavesLeft());
            }
            employee.addLeaves(l);
        } catch (ExLeavesOverLapping e) {
            
            throw e;
        } catch (ExFinalStageOverlap e) {
            
            throw e;
        }
    }

    public void listLeaves() {
        for(Employee e: allEmployees){
            e.printLeaves();
        }
    }

    public void listLeaves(String employee) {
        Employee e = findEmployee(employee); 
        e.printLeaves();
    }

    public void adjustLeaves() {
        for(Employee e: allEmployees){
            e.updateLeaves();
        }
    }

    public ArrayList<Project> findProjectFromTeam(Team a){
        ArrayList <Project> projects = new ArrayList<>();
        for (ProjectAssignment p : allAssignments) {
            if(p.getTeam()==a){
                projects.add(p.getProject());
            }
        }
        return projects;
    }

    public void listTeamMembers(String t) {
        Team team = findTeam(t);
        team.listMembers();
    }

    public void removeLeaves(String employeeName, Leave l) {
        Employee e = findEmployee(employeeName);
        e.removeLeave(l);
    }

    public void removeFromTeam(Team t, Employee e) {
        t.removeMember(e);
    }

    public void suggestTeam(Project p) {
        System.out.println("During the period of project "+p.getProjName()+" ("+p.getStartDay() +" to "+p.getEndDay()+"):");
        System.out.println("Average manpower (m) and count of existing projects (p) of each team:");
        Team suggestedTeam = null;
        double loadFactor = 1000;
        for(Team t: allTeams){
            System.out.printf("%s: m=%.2f workers, p=%.2f projects",t.getTeamName(),t.getManPower(p),t.getProjectCount(allAssignments, p));
            System.out.println();
            if(t.getLoadingFactor(allAssignments, p)<loadFactor){
                suggestedTeam = t;
                loadFactor = t.getLoadingFactor(allAssignments, p);
            }
        }
        System.out.println("Projected loading factor when a team takes this project "+p.getProjName() +":");
        for(Team t: allTeams){
            System.out.printf("%s: (p+1)/m = %.2f",t.getTeamName(),t.getLoadingFactor(allAssignments, p));
            System.out.println();
        }
        System.out.printf("Conclusion: %s should be assigned to %s for best balancing of loading\n", p.getProjName(), suggestedTeam.getTeamName());

    }

}
