package onlinePackagedFoodStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class Runner {
	public static void main(String[] args) {
		StackArray<TunaCan> tunaCanPile= pileOfPackedFoods(30, 1); //to create TunaCan pile
		StackArray<CornCan> cornCanPile = pileOfPackedFoods(30, 2); //to create CornCan pile
		StackArray<PudingPacket> pudingPacketPile = pileOfPackedFoods(30, 3); //to create PudinPacket pile
		StackArray<InstantNoodlePacket> instantNoodlePacketPile = pileOfPackedFoods(30, 4); //to create InstantNoodlePacket pile
		
		//printing the newly created stacks
		System.out.println("\nStack of Tuna Can:\n\n");
		stackPrinter (tunaCanPile, "tunaCan");
		System.out.println("\nStack of Corn Can:\n\n");
		stackPrinter (cornCanPile, "cornCan");
		System.out.println("\nStack of Puding Packet:\n\n");
		stackPrinter (pudingPacketPile, "pudingPacket");
		System.out.println("\nStack of Instant Noodle Packet:\n\n");
		stackPrinter (instantNoodlePacketPile, "instantNoodlePacket");
		
		//reading csv file
		BufferedReader ordersFile = null;
		try {
			ordersFile= new BufferedReader(new FileReader("src/orders.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//adding order objects to the queue
		System.out.println("\n\n-----------------------------------------------");
		System.out.println("Order id Order date\tFood categories (1,2,3)\n");
		
		ArrayQueue<Orders> orderWaitingLine = new ArrayQueue<Orders>();
		for (int i=0; i<30; i++) {
			String line = null;
			try {
				line = ordersFile.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] arrayOfLine = line.split(",");
			String id = arrayOfLine[0];
			String expirationDate = arrayOfLine[1];
			String[] arrayOfFoodCatagories = { arrayOfLine[2].strip(), arrayOfLine[3].strip(), arrayOfLine[4].strip()};
			Orders newOrder = new Orders(id, expirationDate, arrayOfFoodCatagories);
			orderWaitingLine.enqueue(newOrder);
		}
		
		try {
			ordersFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//processing orders 
		MyLinkedList<CargoPackets> listOfCargoPackets = new MyLinkedList<>();
		proccesTheOrders(listOfCargoPackets, orderWaitingLine, tunaCanPile, cornCanPile, pudingPacketPile, instantNoodlePacketPile);
		
		//printing the stacks after removing items
		System.out.println("\nRemaining Tuna Can: \n");
		stackPrinter (tunaCanPile, "tunaCan");
		stackPrinter (cornCanPile, "cornCan");
		System.out.println("\nRemaining Puding Packet:\n");
		stackPrinter (pudingPacketPile, "pudingPacket");
		System.out.println("\nRemaining Noodle Packet:\n");
		stackPrinter (instantNoodlePacketPile, "instantNoodlePacket");
	
		
		//printing the list of cargo packages
		System.out.println("\nList of Cargo Packets: ");
		System.out.println("------------------------\n");
		System.out.println("Id\tOrder Date\tFood Category 1\tFood Category 2\tFood Category 3");
		cargoPacketPrinter(listOfCargoPackets);
		
		//printing expiration dates of the packaged products of 25th cargoPacket
		System.out.println("\n----------------");
		System.out.println("Cargo Packet 25: ");
		System.out.println("----------------\n");
		CargoPackets twentyFifthCargoPacket = listOfCargoPackets.getEntry(25);
		if (twentyFifthCargoPacket.getCategoryA()!=null) {
			System.out.println(twentyFifthCargoPacket.getCategoryA().getName()+"\t\t\t --> "+twentyFifthCargoPacket.getCategoryA().getExpirationDate()+"\n");
		}
		if (twentyFifthCargoPacket.getCategoryB()!=null) {
			System.out.println(twentyFifthCargoPacket.getCategoryB().getName()+"\t\t\t -->  "+twentyFifthCargoPacket.getCategoryB().getExpirationDate()+"\n");
		}
		if (twentyFifthCargoPacket.getCategoryC()!=null) {
			System.out.println(twentyFifthCargoPacket.getCategoryC().getName()+"\t -->  "+twentyFifthCargoPacket.getCategoryC().getExpirationDate()+"\n");
		}
		if (twentyFifthCargoPacket.getCategoryD()!=null) {
			System.out.println(twentyFifthCargoPacket.getCategoryD().getName()+"\t -->  "+twentyFifthCargoPacket.getCategoryD().getExpirationDate()+"\n");
		}
		
		//printing the list again after removing the cargo packet with ID 20
		listOfCargoPackets.remove(20);
		System.out.println("\nList of Cargo Packets after removing the cargo packet 20: ");
		System.out.println("-----------------------------------------------------------\n");
		System.out.println("Id\tOrder Date\tFood Category 1\tFood Category 2\tFood Category 3");
		
		cargoPacketPrinter(listOfCargoPackets);
	}
	private static void proccesTheOrders(MyLinkedList<CargoPackets> listOfCargoPackets, ArrayQueue<Orders> orderWaitingLine, StackArray<TunaCan> tunaCanPile,
			StackArray<CornCan> cornCanPile, StackArray<PudingPacket> pudingPacketPile, StackArray<InstantNoodlePacket> instantNoodlePacketPile) {
		for (int i = 0; i<30; i++) {              //Because linked list don't let us to add item to uncreated node. We create nodes with null datas.
			listOfCargoPackets.add(null);
		}
		for (int i = 0; i<30; i++) {
			Orders oldestOrder = orderWaitingLine.dequeue();
			//printing waiting line
			System.out.print(oldestOrder.getId()+"\t");
			System.out.print(oldestOrder.getOrderDate()+"\t");
			System.out.print(oldestOrder.getFoodCatagories()[0]+"\t");
			System.out.print(oldestOrder.getFoodCatagories()[1]+"\t");
			System.out.println(oldestOrder.getFoodCatagories()[2]);
			
			String cargoPacketId = oldestOrder.getId();
			String cargoPacketProccesDate = oldestOrder.getOrderDate();
			CargoPackets newCargoPacket = new CargoPackets(cargoPacketId, cargoPacketProccesDate);
			for (String foodCatagory: oldestOrder.getFoodCatagories()) {
				switch (foodCatagory) {
				case "tuna":
					TunaCan removedTuna = tunaCanPile.pop();
					newCargoPacket.setCatagory(removedTuna);
					break;
				case "corn":
					CornCan removedCorn= cornCanPile.pop();
					newCargoPacket.setCatagory(removedCorn);
					break;
				case "pudding":
					PudingPacket removedPudding = pudingPacketPile.pop();
					newCargoPacket.setCatagory(removedPudding);
					break;
				case "noodle":
					InstantNoodlePacket removedNoodle = instantNoodlePacketPile.pop();
					newCargoPacket.setCatagory(removedNoodle);
					break;
				}
			}
			listOfCargoPackets.replace(Integer.parseInt(cargoPacketId),newCargoPacket);
		}
	}
	
	private static void cargoPacketPrinter(MyLinkedList <CargoPackets> listOfCargoPackets) {
		for (int i = 1 ; i < listOfCargoPackets.getLength()+1; i++) {
			CargoPackets printedCargoPacket = listOfCargoPackets.getEntry(i);
			System.out.print("\n"+ printedCargoPacket.getId()+"\t");
			System.out.print(printedCargoPacket.getProccesDate()+"\t");
			if (printedCargoPacket.getCategoryA()!=null) {
				System.out.print(printedCargoPacket.getCategoryA().getName()+"\t\t");
			}
			if (printedCargoPacket.getCategoryB()!=null) {
				System.out.print(printedCargoPacket.getCategoryB().getName()+"\t\t");
			}
			if (printedCargoPacket.getCategoryC()!=null) {
				System.out.print(printedCargoPacket.getCategoryC().getName()+"\t");
			}
			if (printedCargoPacket.getCategoryD()!=null) {
				System.out.print(printedCargoPacket.getCategoryD().getName()+"\t");
			}
			System.out.println();
		}	
	}
	
	private static <T> StackArray<T> pileOfPackedFoods(int numberOfPackedFoods, int typeOfFood){// Generic method created because different type of objects will be used
		assert(typeOfFood==1 || typeOfFood==2 ||typeOfFood==3 ||typeOfFood==4);
		String[] listOfMonths = {"January", "February", "March", "April", "May", "June", 
		                                        "July", "August", "September", "October", "November", "December"} ;
		String[] listOfYears = {"2022", "2023", "2024"};
		StackArray<T> foodStack = new StackArray<>() ;
		
		switch (typeOfFood) {
			case 1:
				for(int i = 0; i < numberOfPackedFoods; i++) {
					String month = listOfMonths[11-(i%12)];   //mod is used to get smaller number than 12
					String year = listOfYears[2-(i/12)];	//mod is used to get smaller number than 
					@SuppressWarnings("unchecked")
					T tunaCan = (T) new TunaCan(month+"-"+year);
					foodStack.push(tunaCan);
				}
				break;
			case 2:
				for(int i = 0; i < numberOfPackedFoods; i++) {
					String month = listOfMonths[11-(i%12)];   //mod is used to get smaller number than 12
					String year = listOfYears[2-(i/12)];	//mod is used to get smaller number than 
					@SuppressWarnings("unchecked")
					T cornCan = (T) new CornCan(month+"-"+year);
					foodStack.push(cornCan);
				}
				break;
			case 3:
				for(int i = 0; i < numberOfPackedFoods; i++) {
					String month = listOfMonths[11-(i%12)];   //mod is used to get smaller number than 12
					String year = listOfYears[2-(i/12)];	//mod is used to get smaller number than 
					@SuppressWarnings("unchecked")
					T pudingPacket = (T) new PudingPacket(month+"-"+year);
					foodStack.push(pudingPacket);
				}
				break;
			case 4:
				for(int i = 0; i < numberOfPackedFoods; i++) {
					String month = listOfMonths[11-(i%12)];   //mod is used to get smaller number than 12
					String year = listOfYears[2-(i/12)];	//mod is used to get smaller number than 
					@SuppressWarnings("unchecked")
					T instantNoodlePacket = (T) new InstantNoodlePacket(month+"-"+year);
					foodStack.push(instantNoodlePacket);
			}	
			break;
		}
		return foodStack;
	}

	private static <T> void stackPrinter(StackArray<T> stack, String nameOfStack) {
		StackArray<T> tempStack = new StackArray<>();
		if (nameOfStack == "tunaCan") {
			while (!stack.isEmpty()) {
				T displayedFood = stack.pop();
				tempStack.push( displayedFood);
				System.out.print(((TunaCan)displayedFood).getName()+"\t\t\t");
				System.out.println(((TunaCan)displayedFood).getExpirationDate());
			}
			while (!tempStack.isEmpty()) {
				T removedFood = tempStack.pop();
				stack.push((T) removedFood);
			}
		}else if (nameOfStack == "cornCan") {
			while (!stack.isEmpty()) {
				T displayedFood =  stack.pop();
				tempStack.push( displayedFood);
				System.out.print(((CornCan)displayedFood).getName()+"\t\t\t");
				System.out.println(((CornCan)displayedFood).getExpirationDate());
			}
			while (!tempStack.isEmpty()) {
				T removedFood = tempStack.pop();
				stack.push(removedFood);
			}
		}else if (nameOfStack == "pudingPacket"){
			while (!stack.isEmpty()) {
				T displayedFood = stack.pop();
				tempStack.push(displayedFood);
				System.out.print(((PudingPacket)displayedFood).getName()+"\t\t");
				System.out.println(((PudingPacket)displayedFood).getExpirationDate());
			}
			while (!tempStack.isEmpty()) {
				T removedFood = tempStack.pop();
				stack.push(removedFood);
			}
		}else if (nameOfStack == "instantNoodlePacket") {
			while (!stack.isEmpty()) {
				T displayedFood = stack.pop();
				tempStack.push(displayedFood);
				System.out.print(((InstantNoodlePacket)displayedFood).getName()+"\t");
				System.out.println(((InstantNoodlePacket)displayedFood).getExpirationDate());
			}
			while (!tempStack.isEmpty()) {
				T removedFood = tempStack.pop();
				stack.push(removedFood);
			}
		}
	}
}

	