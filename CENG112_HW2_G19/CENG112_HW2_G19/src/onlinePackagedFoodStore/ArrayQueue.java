package onlinePackagedFoodStore;

import java.util.NoSuchElementException;

public class ArrayQueue <T>{

	private T[] queue;
	private boolean initialized=false;
	private int frontIndex;
	private int backIndex;
	private static int maxCapacity = 10000;
	private static int defaultCapacity = 30; // we need 30 items in our order queue
	
	public ArrayQueue(){
		this(defaultCapacity);
	}
	
	public ArrayQueue(int initialCapacity) {
		checkCapacity(initialCapacity);
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity]; 
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity-1;
	}
	
	private void checkCapacity(int length) {
		if (length == maxCapacity) {
			throw new SecurityException("Maximum capacity limit has been reached");
		}
	}
	
	private void initialized() {
		if (!initialized == false) {
			throw new SecurityException("Queue has not been initialized properly");
		}
	}
	
	private void ensureCapacity() {
		if ((backIndex+2)%queue.length==frontIndex) {
			int oldCapacity = queue.length;
			int newCapacity = oldCapacity * 2;
			checkCapacity(newCapacity);
			@SuppressWarnings("Unchecked")
			T[] tempQueue = (T[]) new Object[newCapacity];
			for(int i=0; i<queue.length-1;i++) {
				tempQueue[i] = queue[frontIndex];
				frontIndex = (frontIndex + 1) % queue.length;
			}
			queue=tempQueue;
			frontIndex = 0; // starting from the beginning of the queue
			backIndex = oldCapacity-2;// the reason of subtraction is length function has one value more and unused location
		}	
	}
	
	public boolean isEmpty() {
		boolean result = false;
		if ((backIndex+1)%queue.length == frontIndex) {
			result = true;
		}
		return result; 
	}
	
	public void enqueue(T object) {
		initialized();
		ensureCapacity();
		backIndex = (backIndex +1)%queue.length;
		queue[backIndex] = object;
	}
	
	public T dequeue() {
		initialized();
		T firstItem;
		if (isEmpty()) {
			throw new NoSuchElementException();   // I couldn't find EmptyQueueException which used in the implementation of queue in pdf
		}else {
			firstItem = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex+1)%queue.length;
		return firstItem;
		}
	}
	
	public T getFront() {
		initialized();
		if (isEmpty()) {
			throw new NoSuchElementException();
		}else {
			return queue[frontIndex];
		}
	}
	public void clear() {
		initialized();
		if (isEmpty()) {
			throw new NoSuchElementException();
		}else {
			while (!(queue[frontIndex]==null)){		
				dequeue();
			}
		}
	}
}
