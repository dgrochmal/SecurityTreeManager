package edu.ncsu.csc316.security_manager.attack;


/**
 * Object to hold an AttackStep of an attack tree
 * @author Dan Grochmal djgrochm
 */
public class AttackStep {

	/** Field to hold whether a AttackStep is an AND or an OR */
	public boolean andOr;
	/** Field to hold the description of an AttackStep */
	public String desc;
	/** Field to hold the impact of an AttackStep */
	public double impact;
	/** Field to hold the cost of an AttackStep */
	public double cost;
	/** Field to hold the probability of an AttackStep */
	public double probability;
	
	
	/**
	 * Constructor of an AttackStep, will be a GOAL step since there are no other values
	 * @param desc Description of an attack step
	 */
	public AttackStep(String desc) {
		this.desc = desc;
		this.impact = -1.0;
		this.cost = 0.0;
		this.probability = 0.0;
	}
	
	/**
	 * Constructor of an AttackStep that includes a description and an AND/OR value
	 * 
	 * @param andOr true if AND, false if OR
	 * @param desc The description of an AttackStep
	 */
	public AttackStep(boolean andOr, String desc) {
		this.andOr = andOr;
		this.desc = desc;
		this.impact = 0.0;
		this.cost = 0.0;
		this.probability = 0.0;
	}
	
	/**
	 * Constructor that includes all values for an AttackStep
	 * 
	 * @param andOr Value that is AND if true, OR if false
	 * @param desc Description of an AttackStep
	 * @param impact The impact value of a step
	 * @param cost The cost value of a step
	 * @param probability the probabilty value of a step
	 */
	public AttackStep(boolean andOr, String desc, double impact, double cost, double probability) {
		this.andOr = andOr;
		this.desc = desc;
		this.impact = impact;
		this.cost = cost;
		this.probability = probability;
	}

	/**
	 * Returns the value of andOr
	 * @return andOr
	 */
	public boolean isAndOr() {
		return andOr;
	}

	/**
	 * Gets the description
	 * @return the description of a AttackStep
	 */
	public String getDesc() {
		return desc;
	}
	
	/**
	 * Gets the impact of a step
	 * @return the impact of a step
	 */
	public double getImpact() {
		return impact;
	}

	/**
	 * Gets the cost of a step
	 * @return the cost of a step
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * Gets the probability of a step
	 * @return the probability of a step
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * Sets the probability of a step
	 * @param probability to be set to a step
	 */
	public void setProbability(double probability) {
		this.probability = probability;
		
	}

	/**
	 * Sets the cost of a step
	 * @param cost to be set to a step
	 */
	public void setCost(double cost) {
		this.cost = cost;
		
	}

	/**
	 * Sets the impact of a step
	 * @param impact to be set to a step
	 */
	public void setImpact(double impact) {
		this.impact = impact;
		
	}

	/**
	 * Outputs the AttackStep as a String
	 * @return the AttackSte[ as a string
	 */
	@Override
	public String toString() {
		String prefix = "";
		if(this.isAndOr()){
			prefix = "AND";
		} else {
			prefix = "OR";
		}
		if(this.getImpact() == -1.0){
			return "GOAL Step[" + this.desc + ", C=0.00, P=0.000, I=0.00]";
		}
		String costS = String.format("%.2f", this.getCost());
    	String impactS = String.format("%.2f", this.getImpact());
    	String probS = String.format("%.3f", this.getProbability());
		StringBuilder s = new StringBuilder();
		return s.append(prefix).append(" Step[").append(this.getDesc()).append(", C=").append(costS).append(", P=").append(probS).append(", I=").append(impactS).append("]").toString();

	}

}