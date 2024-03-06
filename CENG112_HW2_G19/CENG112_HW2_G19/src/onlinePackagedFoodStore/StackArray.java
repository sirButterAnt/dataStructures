package onlinePackagedFoodStore;

import java.util.EmptyStackException;

public class StackArray <T> {
	
	private T[] stack; //Using array implementation .
	private int topIndex; // storing top index to use in push, peek, pop methods.
	private boolean initialized;//to avoid uninitialized fields in methods.
	private static final int defaultCapacity=30; //all the  pile of packaged foods has 30 items stack
	private static final int maxCapacity=10000;// to disable the user pass maximum capacity
	
	public StackArray () {
		this(defaultCapacity);
	}

	public StackArray(int initialCapacity){ //second constructor to create preferred capacity
		checkCapacity(initialCapacity);
		@SuppressWarnings("Unchecked")
		T[] tempStack= (T[]) new Object[initialCapacity];
		stack=tempStack;
		topIndex=-1; //-1 because object hasn't initialized yet
		initialized=true;
	}
	
	private void checkInitialized() { // to make code secure
		if (!initialized) {
			throw new SecurityException("StackArray has not been initialized properly");
		}
	}
	
	private void checkCapacity(int newLength) {
		if (newLength==maxCapacity) {
			throw new SecurityException("Maximum capacity has been reached");
		}
	}
		
	private void newCapacity(int length) {
		@SuppressWarnings("unchecked")
		T[] tempStack=(T[]) new Object[length]; 
		for(int i=0;i<stack.length;i++)
			tempStack[i]=stack[i];
		stack=tempStack;
	}
	
	private void ensureCapacity() {
		if (topIndex==stack.length-1) {
			int newLength=2 * (topIndex-1);
			checkCapacity(newLength);
			newCapacity(newLength);	
		}
	}
	
	public boolean isEmpty() {
		checkInitialized();
		boolean result=false;
		if (topIndex==-1) {
			result=true;
		}
		return result;
	}
	
	public void push(T item) {
		checkInitialized();
		ensureCapacity();
		stack[topIndex+1]=item;
		topIndex++;
	}
	
	public T pop() {
		checkInitialized();
		T result;
		if (!isEmpty()) {
			result=stack[topIndex];
			stack[topIndex]=null;
			topIndex--;
		}else {
			throw new EmptyStackException();
		}
		return result;
	}
	
	public T peek() {
		checkInitialized();
		T result=null;
		if(!isEmpty()) {
			result=stack[topIndex];
		}
		return result;
	}
	
	public void clear() {
		checkInitialized();
		if(!isEmpty()) {
			for(int i=0;i<topIndex+1;i++) {
				stack[i]=null;
			}
		}
	}	
	
}