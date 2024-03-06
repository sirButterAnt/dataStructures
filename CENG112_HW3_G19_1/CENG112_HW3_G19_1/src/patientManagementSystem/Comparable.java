package patientManagementSystem;

public interface Comparable<T> {
	// We use comparable interface in order to make lower bound of given sorted list type parameter .
	public  int compareTo(T other);
	
	// In order to compare two objects in priority queue
	public int compareToPriority(T other);
}
