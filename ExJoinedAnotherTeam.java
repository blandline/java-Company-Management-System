public class ExJoinedAnotherTeam extends Exception {
    public ExJoinedAnotherTeam() { super("Already joined another team: team"); } 
    public ExJoinedAnotherTeam(String message){ super(message); }
}
