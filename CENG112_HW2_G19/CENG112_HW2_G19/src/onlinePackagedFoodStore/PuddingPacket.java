package onlinePackagedFoodStore;

class PudingPacket {
	private static String netWeight = "120 gr";
	private static String flavor = "Banana";
	private String expirationDate ;
	private static String name = "pudingPacket";
	
	public PudingPacket(String expirationDate) {
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
	
	public String getFlover(){
		return flavor;
	}
}
