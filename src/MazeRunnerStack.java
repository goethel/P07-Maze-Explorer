import java.util.EmptyStackException;


/** This class is an implementation of a Stack ADT, which holds Position objects, and is used to hold a 
 * stack of objects, allowing for manipulation of the stack
 * @author andrew
 *
 */
public class MazeRunnerStack implements StackADT<nick> {
	private nick stackHead;
	private int stackSize = 0;
	private nick[] stackArray;
	/** No argument constructor for MazeRunnerStack. Allows for creating of a MazeRunnerStack without
	 * arguments.
	 * 
	 */
	public MazeRunnerStack() {
	}
	/** Constructor for MazeRunnerStack. Used for testing purposes, to allow precise testing variables 
	 * without modifying the object itself
	 * @param stackHead // head of stack
	 * @param stackSize // size of Stack
	 * @param stackArray // the array containing Position objects
	 */
	public MazeRunnerStack(nick stackHead, int stackSize , nick[] stackArray) {
		this.stackHead = stackHead;
		this.stackSize = stackSize;
		this.stackArray = stackArray;
	}
	/* 
	 * @see StackADT#push(java.lang.Object)
	 */
	public void push(nick newPosition) {
		// detects if new Position is null, throwing an exception
		if(newPosition == null) {
			throw new IllegalArgumentException("ERROR: ATTEMPTED TO PUSH A NULL POSITION TO STACK");
		}
		// if the stack is empty, the new element is the first element in the stack
		if (isEmpty() == true) {
			stackArray = new nick[10];
			++stackSize;
			stackArray[0] = newPosition;
		}
		else {
			// if the stack has reached maximum size, the underlying oversized array is increased to 
			// accomodate that
			if(stackSize == stackArray.length) {
				nick[] biggerArray = new nick[stackArray.length*2];
				// for loop to copy contents of original array to new, larger array
				for(int i = 0 ; i < stackArray.length ; ++i) {
					biggerArray[i+1] = stackArray[i];
				}
				stackArray = biggerArray;
				stackArray[0] = newPosition;
				// Test Queue
				//stackArray[stackSize] = newPosition;
				++stackSize;
			}
			else {
				nick[] newArray = new nick[stackArray.length];
				// for loop to shift contents of stack back
				for(int i = 0 ; i < stackSize; ++i) {
					newArray[i+1] = stackArray[i];
					//newArray[i] = stackArray[i];
				}
				newArray[0] = newPosition;
				// 				Test Queue
				//				newArray[stackSize-1] = newPosition;

				stackArray = newArray;
				++stackSize;
			}

		}
	}

	/* 
	 * @see StackADT#pop()
	 */
	@Override
	public nick pop() throws EmptyStackException {
		nick[] popped = new nick[1];
		// detects if stack is empty, throwing an exception if so
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			// for loop to copy the contents of the original array into the new array,
			// sans the popped Position object, and everything is shifted down to cover the opening
			nick[] newArray = new nick[stackArray.length];
			for(int i = 1 ; i < stackSize; ++i) {
				newArray[i-1] = stackArray[i];
			}
			popped[0] = stackArray[0] ;
			stackArray = newArray;
			--stackSize;		
			return popped[0];
		}

	}

	/* 
	 * @see StackADT#peek()
	 */
	@Override
	public nick peek() throws EmptyStackException {
		// detects if attempting to peek an empty stack, throwing an exception if so.
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		// returns the most recent object in the stack
		else {
			return stackArray[0];

		}
	}

	/* 
	 * @see StackADT#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		//detects if the stack is empty, by using stackSize
		if (stackSize == 0) {
			return true;
		}
		return false;
	}
	public boolean contains(nick p) { 
		// detects if the stack is empty
		if (isEmpty()) {
			return false;
		}
		// detects if the position object passed in is null
		else if(p == null) {
			return false;
		}
		else {
			// for loop to compare the values of the Position objects in the 
			// stack array with the values of the passed in Position object
			for(int i = 0 ; i < stackSize-1 ; ++i) {
				if(stackArray[i] != null && stackArray[i].row == p.row && stackArray[i].col == p.col) {
					return true;
				}
			}
		}
		return false;
	} //Reports whether the Position p can be found within the stack


}
/** This class contains the information of a particular cell, when used in conjuction with the stack
 * allows the program to keep a running list of the coordinates it has visited in order to solve a maze
 * @author andrew
 *
 */
class nick {
	int col;
	int row;

	nick(int row, int col) {
		this.col = col;
		this.row = row;
	}
	boolean equals(nick other) {
		return this.col==other.col && this.row==other.row;
	}
}
