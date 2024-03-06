package onlinePackagedFoodStore;

public class MyLinkedList <T>{

	private Node firstNode;
	private int numberOfEntries;
	
	public MyLinkedList() {
		initializeDataFields(); 
	}
	
	public void clear() {
		initializeDataFields();//Leave old data to garbage collector.
	}
	
	public boolean isEmpty() {
		boolean result ;
		if (numberOfEntries == 0){
			assert (firstNode == null);
			result = true;
		}else {
			assert (!(firstNode == null));
			result = false;
		}
		return result;
	}
	
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			firstNode = newNode;
		}else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);
		}
		numberOfEntries++;
	}
	
	public void add(int position, T newEntry ) {
		assert(position >=1 && position <= numberOfEntries+1);//the reason of adding 1 is that addition of the new entry at end of the list
		Node newNode = new Node (newEntry);
		if (position == 1) {
			newNode.setNextNode(firstNode);
			firstNode = newNode;	
		}else {
			Node beforeNode = getNodeAt(position-1);
			Node afterNode = beforeNode.getNextNode();
			newNode.setNextNode(afterNode);
			beforeNode.setNextNode(newNode);
			}
	}
	
	public T remove(int position) {
		assert((position >= 1) && (position <= numberOfEntries)&& (!isEmpty())):"Out of bounds error";
		T result;
		if (position == 1) {
			result = firstNode.getData();
			firstNode = firstNode.getNextNode();
		}else {
			Node beforeNode = getNodeAt(position-1);
			Node removedNode = beforeNode.getNextNode();
			Node afterNode = removedNode.getNextNode();
			beforeNode.setNextNode(afterNode);
			result = removedNode.getData();
		}
		numberOfEntries--;
		return result;
	}
	
	public T replace(int position, T newEntry) {
		if( (position >= 1) &&( position <= numberOfEntries) ) {
			Node oldNode = getNodeAt(position);
			T oldEntry = oldNode.getData();
			oldNode.setData(newEntry);
			return oldEntry;
		}else {
			throw new IndexOutOfBoundsException("Given position of the new entry is not in the bound of the list");
		}
	}
	
	public T[] toArray() {
		Node nodes = firstNode; 
		@SuppressWarnings("Unchecked")
		T[] arrayOfNodes =(T[]) new Object[numberOfEntries]; 
		for(int i=0; i<numberOfEntries; i++) {
			assert(nodes.getNextNode() != null);
			arrayOfNodes[i] = nodes.getData();
			nodes = nodes.getNextNode();
		}
		assert (nodes.getData() == null);  //Assumption about last node which should be empty
		return arrayOfNodes;
	}
	
	public boolean contains(T newEntry) {
		Node searchedNodes = firstNode;
		boolean result = false;
		int i = 0;
		while (i < numberOfEntries && searchedNodes != null) {
			if (newEntry == searchedNodes.getData()) {
				result = true;
				break;
			}else {
			searchedNodes = searchedNodes.getNextNode();
			i++;
			}
		}
		return result;
	}
	
	public int getLength() {
		return numberOfEntries;
	}
	
	public T getEntry(int position) {
		return getNodeAt(position).getData();
	}
	

	private void initializeDataFields() { //this function will be used in multiple place 
		firstNode = null;
		numberOfEntries = 0;
	}
	
	private Node getNodeAt(int position){
		assert (position <= numberOfEntries && position >= 1 && firstNode != null);
		Node lastNode=firstNode;
		for (int i = 1; i < position; i++) {
			lastNode=lastNode.getNextNode();
		}
		assert (lastNode == null);
		return lastNode;
	}
	
	
	private class Node { // inner class
		private T data;
		private Node next;
		
		private Node(T data) {        //to create first node
			this(data, null);
		}
		
		private Node(T data, Node node) {
			this.data=data;
			this.next=node;
			
		}
		private Node getNextNode() {
			return next;
		}
		
		private void setNextNode(Node node) {
			next = node;
		}
		
		private T getData() {
			return data;
		}
		
		private void setData(T newData) {
			data = newData;
		}
	}
}

