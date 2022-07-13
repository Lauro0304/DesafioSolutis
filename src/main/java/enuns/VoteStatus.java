package enuns;

public enum VoteStatus {
	SIM("SIM"),
    NAO("NAO");
	
	private final String label;
	
	VoteStatus(String label){
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
