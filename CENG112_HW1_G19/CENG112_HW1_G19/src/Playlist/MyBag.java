package Playlist;
//Serhat Eren TAS 280201020    Kerem Bugra KASAL 280201005 group 19
public class MyBag<T> { // bag class

private int numberOfEntries;
private T bag[];
private final int maxCapacity=1000;
private boolean initialized=false;
private int defaultCapacity=25;


MyBag(){
	@SuppressWarnings("unchecked")
	T[] temp=(T[]) new Object[defaultCapacity];
	this.bag=temp;
	numberOfEntries=0;
	initialized=true;
	
}

MyBag(int contains){
	if(contains<=maxCapacity) {
		@SuppressWarnings("unchecked")
		T[] tempBag=(T[])new Object[contains]; 
		this.bag=tempBag;
		numberOfEntries=0;
		initialized=true;
	}else {
		throw new IllegalStateException("attempt to create a bag exceeds allowed capacity ");
	}
}

public boolean isEmpty() {
	checkInitialized();
	return (numberOfEntries==0);
}
//copied to temporary list.Double the original size and paste temporary into original reference. 
private void doubleCapacity() {
	int doubleLength=2*bag.length;
	T[] temp=bag;
	@SuppressWarnings("unchecked")
	T[] newOne=(T[]) new Object[doubleLength];     //to not run out of capacity since we want a dynamic sized array
	bag=newOne;
	for(int i=0;i<temp.length;i++) {
		bag[i]=temp[i];
	}
}
public boolean add(T newer) {
	checkInitialized();
	if (isFull()) {
		doubleCapacity();
	}
	bag[numberOfEntries]=newer;
	numberOfEntries++;
	return true;
}
	
public boolean isFull() {
	checkInitialized();
	return (numberOfEntries==bag.length);
}

public T[] toArray() {
	checkInitialized();
	@SuppressWarnings("unchecked")
	T[] result=(T[]) new Track[numberOfEntries];// I couln't understan why should I need to use Track instead of Object
	for(int i=0;i<numberOfEntries;i++) {
		result[i]=bag[i];
	}
	return result;
}

private void checkInitialized() {
	if (!initialized)
		throw new SecurityException("Object has not been initialized yet.");
}

public int getFrequencyOf(T entry) {
	checkInitialized();
	int numberOfItems=0;
	for(int i=0;i<numberOfEntries;i++ ) {
		if (entry.equals(this.bag[i])) {
			numberOfItems++;
		}
	}
	return numberOfItems;
}

public boolean contains(T entry) {
	checkInitialized();
	boolean isIn=false;
	for(int i=0;i<numberOfEntries;i++) {
		if(entry.equals(bag[i])) {
		isIn=true;
		}
	}
	return isIn;
}

public T remove() {
	checkInitialized();
	T temp=null;
	if (numberOfEntries>0) {
		temp=bag[numberOfEntries-1];
		bag[numberOfEntries-1]=null;
		numberOfEntries--;
	}
	return temp;
}
public void clear() {
	while (!isEmpty()) {
		remove();
	}
}

private int getIndexOf(T entry) {
	int index=-1;
	for(int i=0;i<numberOfEntries;i++) {
		if (bag[i]==entry) {
			index=i;
			break;
		}
	}
	return index;
}

private T removeEntry(int index) {
	T returned=null;
	if (!isEmpty()&& index>=0) {
		returned=bag[index];
		bag[index]=bag[numberOfEntries-1];
		bag[numberOfEntries-1]=null;
		numberOfEntries--;
	}
	return returned;
}

public boolean remove(T entry) {
	checkInitialized();
	int index=getIndexOf(entry);
	T removed=removeEntry(index);
	return (removed.equals(entry));
}

	
}
	




	



