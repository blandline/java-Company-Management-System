import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Team implements Comparable<Team>{
    private String teamName;
	private Employee head;
	private Day dateSetup;
	private ArrayList <Employee> teamMembers;
	// private Project proj;

public Team(String n, Employee hd) {
	teamName = n;
    head = hd;
    dateSetup = SystemDate.getInstance().clone();//Set all object fields (Read lab sheet!)
	teamMembers = new ArrayList<>();
	// proj = null;
}
public String getTeamName(){
	return teamName;
}

public void addToTeam(Employee e) throws ExJoinedAnotherTeam{
	Company c = Company.getInstance();
	if(c.joinedAnotherTeam(e)){
		String eString = String.format("%s has already joined another team: %s",e.getName(),c.findTeamFromMember(e).getTeamName());
            throw new ExJoinedAnotherTeam(eString);
	}
	teamMembers.add(e);
	Collections.sort(teamMembers);
}

public String getLeaderName(){
	return this.head.getName();
}

public Employee getHead(){
	return head;
}

public static void list(ArrayList<Team> list) {
	//Learn: "-" means left-aligned
	System.out.printf("%-15s%-10s%-13s\n", "Team Name", "Leader","Setup Date" );
	for (Team t : list)
		System.out.printf( "%-15s%-10s%-13s\n",t.teamName,t.head.getName(),t.dateSetup ); //display t.teamName, etc..
}
@Override
public int compareTo(Team another)  {
	return this.teamName.compareTo(another.teamName);
}
public void listMembers() {
	System.out.printf("%-10s%-10s%-13s\n", "Role", "Name","Current / coming leaves" );
	System.out.printf( "%-10s%-10s%-13s\n","Leader",this.head.getName(),head.getLeaves() );
	for (Employee e : teamMembers) {
		System.out.printf( "%-10s%-10s%-13s\n","Member",e.getName(),e.getLeaves() );
	}

}
public void removeMember(Employee e) {
	teamMembers.remove(e);
}

public Employee findMember(Employee a) {
	for(Employee e: teamMembers){
		if(e==a){
			return e;
		}
	}
	return null;
}
public double getProjectCount(ArrayList<ProjectAssignment> assign,Project p){
	double projCount = 0;
	for(ProjectAssignment a: assign){
		if(a.getTeam()==this){
			projCount+=a.getProject().getProjectRatio(p);
		}
	}

	return projCount;
}

public double getLoadingFactor(ArrayList<ProjectAssignment> assign,Project p){
	double manPower = this.getManPower(p);
	double projCount = this.getProjectCount(assign, p);
	return (1 + projCount) / manPower;
}

public double getManPower(Project p){
	double manPower = 0;
	manPower+=getHead().getManPower(p);
	for (Employee e : teamMembers) {
		manPower+=e.getManPower(p);
	}
	return manPower;
}


}
