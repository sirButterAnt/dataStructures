package patientManagementSystem;

public class MyPriorityQueue<T extends Comparable<? super T>>{  //priority queue which uses linked list 
	private LinkedList<T> linkedList = new LinkedList<>() ;
	
	public void add(T newEntry) {
		int position = getPosition(newEntry);
		linkedList.add(position, newEntry);
	}
	
	public T remove() {
		return linkedList.remove(1);
	}
	
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}
	public T peek() {
		return linkedList.getEntry(1);
	}
	
	public void clear() {
		linkedList.clear();
	}
	
	public int getSize() {
		return linkedList.getLength();
	}
	
	private int getPosition(T anEntry) {
		if (linkedList.isEmpty()) {
			return 1;
		}
		for(int i=1; i<getSize()+1; i++) {
			T beforeEntry = linkedList.getEntry(i);
			if(anEntry.compareToPriority(beforeEntry)<=0) {
				return i;	
			}
		}
		return getSize()+1;
	}
	
	public LinkedList<T> getLinkedList(){
		return linkedList;
	}
		
}

