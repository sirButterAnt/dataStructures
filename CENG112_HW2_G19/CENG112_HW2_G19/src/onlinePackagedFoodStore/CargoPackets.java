package onlinePackagedFoodStore;

public class CargoPackets {
	private TunaCan categoryA;
	private CornCan categoryB;
	private PudingPacket categoryC;
	private InstantNoodlePacket categoryD;
	private String proccesDate;
	private String Id;

	public CargoPackets(String Id, String proccesDate) {
		this.Id = Id;
		this.proccesDate = proccesDate;
		this.categoryA = null;
		this.categoryB = null;
		this.categoryC = null;
		this.categoryD = null;
	}
	public TunaCan getCategoryA() {
		return categoryA;
	}
	
	public CornCan getCategoryB() {
		return categoryB;
	}
	
	public PudingPacket getCategoryC() {
		return categoryC;
	}
	
	public InstantNoodlePacket getCategoryD() {
		return categoryD;
	}
	
	public String getId() {
		return Id;
	}
	
	public String getProccesDate() {
		return proccesDate;
	}
	
	
	
	public void setCatagory(TunaCan categoryA) {
		this.categoryA = categoryA;
	}
	
	public void setCatagory(CornCan categoryB) {
		this.categoryB = categoryB;
	}
	
	public void setCatagory(PudingPacket categoryC) {
		this.categoryC = categoryC;
	}
	
	public void setCatagory(InstantNoodlePacket categoryD) {
		this.categoryD = categoryD;
	}
}
