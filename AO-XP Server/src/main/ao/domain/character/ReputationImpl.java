package ao.domain.character;

public class ReputationImpl implements Reputation {

	private int assassinPoints;
	private int banditPoints;
	private int bourgeoisPoints;
	private int thiefPoints;
	private int noblePoints;
	private boolean belongsRoyalArmy;
	private boolean belongsChaosLegion;
	
	public ReputationImpl(int assassinPoints, int banditPoints, int bourgeoisPoints, int thieffPoints, int noblePoints, 
			boolean belongsRoyalArmy, boolean belongsChaosLegion) {
		this.assassinPoints = assassinPoints;
		this.banditPoints = banditPoints;
		this.bourgeoisPoints = bourgeoisPoints;
		this.thiefPoints = thieffPoints;
		this.noblePoints = noblePoints;
		this.belongsRoyalArmy = belongsRoyalArmy;
		this.belongsChaosLegion = belongsChaosLegion;
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

	public int getAssassin() {
		return assassinPoints;
	}

	public int getBandit() {
		return banditPoints;
	}

	public int getBourgeois() {
		return bourgeoisPoints;
	}

	public int getThief() {
		return thiefPoints;
	}

	public int getNoble() {
		return noblePoints;
	}
	

	@Override
	public boolean isChaosLegion() {
		return belongsChaosLegion;
	}

	@Override
	public boolean isRoyalArmy() {
		return belongsRoyalArmy;
	}

	@Override
	public void setChaosLegion(boolean belongs) {
		belongsChaosLegion = belongs;
	}
	

	@Override
	public void setRoyalArmy(boolean belongs) {
		belongsRoyalArmy = belongs;
	}

}
