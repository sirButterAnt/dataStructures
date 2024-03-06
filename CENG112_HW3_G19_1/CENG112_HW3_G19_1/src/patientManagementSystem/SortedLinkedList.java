package patientManagementSystem;

public class SortedLinkedList<T extends Comparable<? super T>>{   //In order the use classes which uses comparable interface this expression is necessary
//a linked list structure with both head and tail references 
	private int numberOfEntries;
	private Nodes firstNode;
	
	public SortedLinkedList() {
		initializeDataFields();
		
	}
	
	public void clear() {
		initializeDataFields();
	}

	public void add(T newEntry) {
		Nodes newNode = new Nodes(newEntry) ;
		Nodes beforeNode = getNodeBefore(newEntry);
		if (isEmpty()|| beforeNode== null) {   
			newNode.setNextNode(firstNode);
			firstNode = newNode; 
		}else {
			Nodes afterNode = beforeNode.getNextNode();
			beforeNode.setNextNode(newNode);
			newNode.setNextNode(afterNode);
		}
		numberOfEntries++;
	}
		
	public T remove(int givenPosition) {
		assert(!isEmpty()&& givenPosition>=1 && givenPosition<=numberOfEntries);
		T removedEntry ;
		if(givenPosition==1) {
			Nodes nextNode = firstNode.getNextNode();
			removedEntry = firstNode.getData();
			firstNode = nextNode;
		}else {
			Nodes nodeBefore = getNodeAt(givenPosition-1);
			Nodes removedNode = nodeBefore.getNextNode();
			Nodes afterNode = removedNode.getNextNode();
			removedEntry = removedNode.getData();
			nodeBefore.setNextNode(afterNode);
		}
		numberOfEntries--;
		return removedEntry;
	}
	
	public int getPosition(T anEntry) {
		assert(!isEmpty());
		int position = 1;
		while (position <= getLength() && getEntry(position).compareTo(anEntry) < 0 ) {
			position++; 
		}
		if (position > getLength() || getEntry(position).compareTo(anEntry)!=0){
			position = -position;
		}
		return position;
	}
	
	public boolean remove(T anEntry) {
		int position = getPosition(anEntry);
		boolean result = false ;
		if (position > 0) {
			remove(position);
			result = true;
		}
		return result;
	}
	
	public boolean isEmpty() {
		boolean empty = false ;
		if( numberOfEntries == 0) {
			assert(firstNode==null ); 
			empty = true;
			return empty;
		}
		else {
			assert(!(firstNode==null ));
			return empty;
		}
	}
	
	public Patient[] toArray() {// I couldn't use generic T in here.s
		
		Patient[] arrayOfList =  new Patient[numberOfEntries];
		Nodes currentNode = firstNode;
		for(int i =0; i < numberOfEntries; i++) {
			assert(currentNode.getNextNode() != null);
			arrayOfList[i] = (Patient) currentNode.getData();
			currentNode = currentNode.getNextNode();
		}
		return arrayOfList;
	}
	
	public T replace(int givenPosition, T newEntry) {
		assert(givenPosition>=1 && givenPosition <= numberOfEntries);
		Nodes replacedNode = getNodeAt(givenPosition);
		T removedData=replacedNode.getData();
		replacedNode.setData(newEntry);
		return removedData;
	}
	
	public boolean contains(T anEntry) {
		Nodes currentNode = firstNode;
		boolean result = false;
		for(int i=0; i<numberOfEntries; i++) {
			if (anEntry.equals(currentNode.getData())) {
				assert(currentNode!=null);
				result = true;
				break;
			}
			currentNode = currentNode.getNextNode();
		}
		return result;
	}
	
	public T getEntry(int givenPosition) {
		T returnedEntry = getNodeAt(givenPosition).getData();
		return returnedEntry;
	}
	
	public int getLength(){
		return numberOfEntries;
	}
	
	private Nodes getNodeAt(int givenPosition){
		assert(givenPosition < numberOfEntries && givenPosition >= 1 && firstNode != null); 
		Nodes currentNode = firstNode; 
		for (int i=1; i < givenPosition; i++) {
			currentNode = currentNode.getNextNode();
			assert(currentNode !=null);// to avoid further unnecessary operation
		}
		return currentNode;
	}

	private void initializeDataFields(){
		firstNode = null;
		numberOfEntries = 0;
	}
	
	private Nodes getNodeBefore(T anEntry) {
		Nodes currentNode = firstNode;
		Nodes beforeNode = null;
		while (currentNode !=null &&  anEntry.compareTo(currentNode.getData()) > 0 && currentNode !=null ){
			beforeNode = currentNode;  
			currentNode = currentNode.getNextNode();
			
		}
		return beforeNode;
	}
	
	private class Nodes{
		T data;
		Nodes nextNode;
		
		private Nodes(T data) {
			this(data, null);
		}
		
		private Nodes(T data, Nodes nextNode) {
			this.data = data;
			this.nextNode = nextNode; 
		}
		
		private Nodes getNextNode() {
			return this.nextNode;
		}
		
		private void setNextNode(Nodes newNode) {
			this.nextNode = newNode;
		}
		
		private void setData(T newData) {
			this.data = newData;
		}
		
		private T getData() {
			return this.data;
		}
		
	}
}


