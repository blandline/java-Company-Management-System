public class ExFinalStageOverlap extends Exception{
    public ExFinalStageOverlap(Project p) { super("The leave is invalid.  Reason: Project "+p.getProjName() +" will be in its final stage during "+p.getFinalStart() +" to "+p.getEndDay()+"."); } 
    public ExFinalStageOverlap(String message){ super(message); }
}