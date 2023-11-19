import java.util.ArrayList;

public class Project implements Comparable<Project>{
    private String projName;
    private Day startDay;
    private int daysToComp;
    private Day endDay;


    

    public Project(String pName,String sDay,int d){
        projName = pName;
        startDay = new Day(sDay);
        daysToComp = d;
        endDay = new Day(sDay);
        endDay = endDay.newDay(sDay,d);
    }

    @Override
    public String toString(){
        return projName +"|"+ startDay +"|" +endDay +"|"+ Company.getProjTeam(this.projName);
    }

    public Object getProjName() {
        return projName;
    }

    @Override
    public int compareTo(Project another)  {
        return this.projName.compareTo(another.projName);
    }
    

}
