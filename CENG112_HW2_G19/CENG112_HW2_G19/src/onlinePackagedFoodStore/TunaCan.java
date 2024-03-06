package onlinePackagedFoodStore;

class TunaCan {
	private static String netWeight = "75 gr";     //because every tuna has same properties, static keyword is used
	private static String drainedWeight = "50 gr";
	private static String ingredients = "Tuna, Sunflower, Oil, salt";
	private String expirationDate;
	private static String name = "tunaCan";
	
	public TunaCan(String expirationDate) {
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
	
	public String getIngredients() {
		return ingredients;
	}
	
	public String getExpirationDate() {
		return expirationDate;
	}
}

