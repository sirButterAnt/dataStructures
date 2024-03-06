package onlinePackagedFoodStore;

public class Orders {
	private String id;
	private String orderDate;
	private String[] foodCatagories;
	
	public Orders(String id, String orderDate, String[]foodCatagories) {
		this.id = id;
		this.orderDate = orderDate;
		this.foodCatagories = foodCatagories;
	}
	
	public String getId() {
		return id;
	}
	
	public String[] getFoodCatagories() {
		return foodCatagories;
	}
	
	public String getOrderDate() {
		return orderDate;
	}

	
	
	
 }