package onlinePackagedFoodStore;

class CornCan {
	private static String netWeight = "220 gr";
	private static String drainedWeight = "132 gr";
	private static String productionCountry = "Turkey";
	private String expirationDate;
	private static String name = "cornCan";
	
	public CornCan(String expirationDate) {
		this.expirationDate=expirationDate;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNetWeight() {
		return netWeight;
	}
	
	public String getDrainedWeight() {
		return drainedWeight;
	}
	
	public String getProductionCountry() {
		return productionCountry;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
}