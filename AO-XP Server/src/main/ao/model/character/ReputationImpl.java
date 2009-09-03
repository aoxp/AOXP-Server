package ao.model.character;

public class ReputationImpl implements Reputation {

	private int assassinPoints;
	private int banditPoints;
	private int bourgeoisPoints;
	private int thiefPoints;
	private int noblePoints;
	private boolean belongsToFaction;
	
	public ReputationImpl(int assassinPoints, int banditPoints, int bourgeoisPoints, int thieffPoints, int noblePoints, 
			boolean belongsToFaction) {
		this.assassinPoints = assassinPoints;
		this.banditPoints = banditPoints;
		this.bourgeoisPoints = bourgeoisPoints;
		this.thiefPoints = thieffPoints;
		this.noblePoints = noblePoints;
		this.belongsToFaction = belongsToFaction;
	}

	@Override
	public void addToAssassin(int points) {
		assassinPoints += points;
	}

	@Override
	public void addToBandit(int points) {
		banditPoints += points;
	}

	@Override
	public void addToBourgeois(int points) {
		bourgeoisPoints += points;
	}

	@Override
	public void addToNoblePoints(int points) {
		noblePoints += points;
	}

	@Override
	public void addToThief(int points) {
		thiefPoints += points;
	}

	@Override
	public Alignment getAlignment() {
		long ret = bourgeoisPoints + noblePoints - assassinPoints - banditPoints - thiefPoints;
		
		return ret < 0 ? Alignment.CRIMINAL : Alignment.CITIZEN;
	}

	@Override
	public int getAssassin() {
		return assassinPoints;
	}

	@Override
	public int getBandit() {
		return banditPoints;
	}

	@Override
	public int getBourgeois() {
		return bourgeoisPoints;
	}

	@Override
	public int getThief() {
		return thiefPoints;
	}
	
	@Override
	public int getNoble() {
		return noblePoints;
	}
	

	@Override
	public boolean belongsToFaction() {
		return belongsToFaction;
	}
	

	@Override
	public void setBelongsToFaction(boolean belongs) {
		belongsToFaction = belongs;
		
	}
	
}
