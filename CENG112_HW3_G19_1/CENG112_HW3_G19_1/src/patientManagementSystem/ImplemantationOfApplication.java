package patientManagementSystem;

public class ImplemantationOfApplication {
	public static void run() {
		
		//Printing the list of patients before treatment.
		printerOfPatientsInTheList(Application.listOfPatients);
		
		//Adding patients objects to patient waiting line
		for(int i=1; i<Application.listOfPatients.getLength()+1; i++) {
			Patient patient = Application.listOfPatients.getEntry(i);
			Application.queueOfPatients.add(patient);
		}
		//Printing waiting line of patients 
		LinkedList<Patient> listOfQueue = Application.queueOfPatients.getLinkedList();
		printerOfPatientsInTheList(listOfQueue);
		
		//Printing remaining and final patient that received the treatment
		printQueueInEvery5Treatment( Application.queueOfPatients);
	}
	
	private static void printerOfPatientsInTheList(SortedLinkedList<Patient> list) {
		Patient[] patientArray = list.toArray();
		System.out.println("List Of Patients : ");
		for (int i=0; i<patientArray.length;i++) {
			System.out.print(i+1+")");
			patientArray[i].printer();
		}
		System.out.println("\n");
	}
	private static void printerOfPatientsInTheList(LinkedList<Patient> list) {
		Patient[] patientArray = list.toArray();
		System.out.println("Waiting Line Of Patients : ");
		for (int i=0; i<patientArray.length;i++) {
			System.out.print(i+1+")");
			patientArray[i].printer();
		}
		System.out.println("\n");
	}
	
	private static void printQueueInEvery5Treatment( MyPriorityQueue<Patient> queue ) {
		int lengthOfQueue = queue.getSize();
		LinkedList<Patient> listOfQueue;
		for (int i=1; i<lengthOfQueue+1;i++) {
			Patient removedPatient = queue.remove();
			if(i%5 == 0) {
				System.out.print("Lastly treatment receiver : ");
				removedPatient.printer();
				System.out.println("Remaining Patients : ");
				listOfQueue = queue.getLinkedList();
				printerOfPatientsInTheList(listOfQueue);
			}
		}
	}
}
