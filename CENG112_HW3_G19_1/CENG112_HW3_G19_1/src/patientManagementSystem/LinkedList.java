package patientManagementSystem;
//In order the use classes which uses comparable interface this expression is necessary.
public class LinkedList <T>{
	  
	   //a linked list structure with both head and tail references 
		private int numberOfEntries;
		private Nodes firstNode;
		private Nodes lastNode;
		
		public LinkedList() {
			initializeDataFields();
			
		}
		
		public void clear() {
			initializeDataFields();
		}

		public void add(T newEntry) {
			Nodes newNode = new Nodes(newEntry);
			if(isEmpty()) {
				firstNode = newNode;
			}else {
				lastNode.setNextNode(newNode);
			}
			lastNode = newNode;
			numberOfEntries ++; 
		}
		
		public void add(int newPosition, T newEntry) {
			Nodes newNode = new Nodes(newEntry);
			assert( numberOfEntries+1 >= newPosition && newPosition >= 1 );
			if (isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			}else if(newPosition == 1) {
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}else if(newPosition <= numberOfEntries) {
				Nodes beforeNode = getNodeAt(newPosition-1);
				Nodes afterNode = beforeNode.getNextNode();
				beforeNode.setNextNode(newNode);
				newNode.setNextNode(afterNode);
			}else {
				lastNode.setNextNode(newNode);
				lastNode = newNode;
			}
			numberOfEntries++;
		}	
		
		public T remove(int givenPosition) {
			assert (!isEmpty()&& givenPosition<=numberOfEntries && givenPosition>=1);
			Nodes removedNode ;
			if (givenPosition==1) {
				removedNode = firstNode; 
				firstNode = removedNode.getNextNode();
				if (numberOfEntries == 1 ) {
					lastNode = null;
				}
			}else {
				Nodes beforeNode = getNodeAt(givenPosition-1);
				removedNode = beforeNode.getNextNode();
				Nodes afterNode = removedNode.getNextNode();
				beforeNode.setNextNode(afterNode);
				if (numberOfEntries == givenPosition) {
					lastNode = beforeNode;
				}
			}
			numberOfEntries--;
			return removedNode.getData();
		}
		
		public boolean isEmpty() {
			boolean empty = false ;
			if( numberOfEntries == 0) {
				assert(firstNode==null && lastNode ==null ); 
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
			lastNode = null;
			numberOfEntries = 0;
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
