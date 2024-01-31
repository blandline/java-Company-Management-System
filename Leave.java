public class Leave implements Comparable<Leave> {
    private Day startDay;
    private Day endDay;
    private int difference;
  


    public Leave(String sDay,String eDay){
        startDay = new Day(sDay);
        endDay = new Day(eDay);
        difference = startDay.dayDifference(endDay);
    }


    public int getDifference(){
        return difference;
    }

    @Override
    public String toString(){
        return startDay+" to "+endDay;
    }

    public boolean leaveOverlap(Leave l){
        boolean output = false;
        if((l.getStartDay().compareTo(startDay)==1)&&(l.getStartDay().compareTo(endDay)==-1)){
            output = true;
        }
        if((l.getEndDay().compareTo(startDay)==1)&&(l.getEndDay().compareTo(endDay)==-1)){
            output = true;
        }
        if((startDay.compareTo(l.getStartDay())==1)&&(startDay.compareTo(l.getEndDay())==-1)){
            output = true;
        }
        if((endDay.compareTo(l.getStartDay())==1)&&(endDay.compareTo(l.getEndDay())==-1)){
            output = true;
        }
        return output;
    }

    public boolean startDayOverlap(Project p){
        int startOverlap = this.getStartDay().compareTo(p.getStartDay());
        int endOverlap = this.getStartDay().compareTo(p.getEndDay());
        if(startOverlap>=0 && endOverlap<=0){
            return true;
        }
        return false;
    }

    public boolean endDayOverlap(Project p){
        int startOverlap = this.getEndDay().compareTo(p.getStartDay());
        int endOverlap = this.getEndDay().compareTo(p.getEndDay());
        if(startOverlap>=0 && endOverlap<=0){
            return true;
        }
        return false;
    }

    public int getDaysOverlap(Project p){
        if(this.startDayOverlap(p)&&!this.endDayOverlap(p)){
            return this.getStartDay().dayDifference(p.getEndDay());
        }
        if(!this.startDayOverlap(p)&&this.endDayOverlap(p)){
            return p.getStartDay().dayDifference(this.getEndDay());
        }
        if(this.startDayOverlap(p)&&this.endDayOverlap(p)){
            return this.getStartDay().dayDifference(this.getEndDay());
        }
        return 0;
        
    }

    public Day getEndDay() {
        return endDay;
    }
    public Day getStartDay() {
        return startDay;
    }

    @Override
    public int compareTo(Leave another)  {
        return this.getStartDay().compareTo(another.getStartDay());
    }

}
