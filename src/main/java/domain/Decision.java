package domain;

public enum Decision {
	BET("Bet"), DEAL("Deal"), BASIC_STRATEGY("Basic Strategy"), HIT("Hit"), STAND("Stand"), SPLIT("Split"), DOUBLE(
			"Double");

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
