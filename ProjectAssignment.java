public class ProjectAssignment {
    private Team team;
    private Project project;

    public ProjectAssignment(Team t,Project p){
        team = t;
        project = p;
    }
    public String getTeamName(){
        return this.team.getTeamName();
    }
    public Team getTeam(){
        return this.team;
    }
    public Project getProject(){
        return project;
    }
}
