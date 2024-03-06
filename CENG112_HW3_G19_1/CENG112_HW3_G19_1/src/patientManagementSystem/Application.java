package patientManagementSystem;

public class Application {
	static final String file = "src/records 1.txt";
	static final int disablePriority = 5;
	static final int over65Priority = 4;
	static final int pregnantPriority = 3;
	static final int under18Priority = 2;
	static final int regularPriority = 1;
	
	static final SortedLinkedList<Patient> listOfPatients = FileInputOutput.read(Application.file);//reading file, creating patient objects and adding into sortedLinkedList
	
	static final MyPriorityQueue<Patient> queueOfPatients = new MyPriorityQueue<>();//creating waiting line
	
	public static void main(String[] args) {
		ImplemantationOfApplication.run();
	}
	
}
