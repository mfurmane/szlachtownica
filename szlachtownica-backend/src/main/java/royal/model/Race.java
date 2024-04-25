package royal.model;

public class Race {
	public int lifespan;
	public int childFrom;
	public int wantChildFrom;
	public int childTo;
	public double fertileTime; // % of year
	public double hornyFactor;
	public double loyalFactor;
	public double interracialFactor;
	public double twinsChance;
	public String[] femaleNames;
	public String[] maleNames;
	public String[] middleNames;
	public boolean surname = true;
	public boolean middlename = false;
	public String name;
	public double attachmentFactor;
	public double poliamoricFactor;
	public double jealousFactor;
	public double impulsiveFactor;
	public double proudFactor;
	public double amorousFactor;

	public Race(int lifespan, int childFrom, int wantChildFrom, int childTo, double fertileTime, double hornyFactor,
			double loyalFactor, double interracialFactor, double twinsChance, String[] femaleNames, String[] maleNames,
			String[] middleNames, double attachmentFactor, double poliamoricFactor, double jealousFactor,
			double impulsiveFactor, double proudFactor, double amorousFactor) {
		super();
		this.lifespan = lifespan;
		this.childFrom = childFrom;
		this.wantChildFrom = wantChildFrom;
		this.childTo = childTo;
		this.fertileTime = fertileTime;
		this.hornyFactor = hornyFactor;
		this.loyalFactor = loyalFactor;
		this.interracialFactor = interracialFactor;
		this.twinsChance = twinsChance;
		this.femaleNames = femaleNames;
		this.maleNames = maleNames;
		this.middleNames = middleNames;
		this.attachmentFactor = attachmentFactor;
		this.poliamoricFactor = poliamoricFactor;
		this.jealousFactor = jealousFactor;
		this.impulsiveFactor = impulsiveFactor;
		this.proudFactor = proudFactor;
		this.amorousFactor = amorousFactor;
	}
}
