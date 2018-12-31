package domain;

public enum Decision {
	BET("Bet"), BASIC_STRATEGY("Basic Strategy"), HIT("Hit"), STAND("Stand"), DOUBLE("Double"), SPLIT("Split");

	private String name;

	Decision(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Decision getDecisionFromName(String name) {
		for (Decision decision : Decision.values()) {
			if (name.equals(decision.getName())) {
				return decision;
			}
		}
		return null;
	}

}
