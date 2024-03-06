package onlinePackagedFoodStore;

class InstantNoodlePacket {
	private static String netWeight = "120 gr";
	private static String simmerDuration = "3 min";
	private String expirationDate;
	private static String name = "instantNoodlePacket";
	
	public InstantNoodlePacket(String expirationDate) {
		this.expirationDate = expirationDate;
	}	
	
	public String getName() {
		return name;
	}
	
	public String getNetWeight() {
		return netWeight;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
	
	public String simmerDuration() {
		return simmerDuration;
	}
}