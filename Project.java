import java.util.ArrayList;

public class Project {
    private String projName;
    private Day startDay;
    private int daysToComp;
    private Day endDay;


    

    public Project(String pName,String sDay,int d){
        projName = pName;
        startDay.set(sDay);
        daysToComp = d;
        endDay.newDay(sDay,d);
    }

    @Override
    public String toString(){
        return projName;
    }
    

}
