package enuns;

public enum VoteEnum {
	
	SIM("SIM"),
	NAO("NAO");

	private final String label;

	VoteEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
