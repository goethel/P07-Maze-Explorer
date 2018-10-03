import java.util.EmptyStackException;

/** This class is a test rig of sorts, running a battery of tests against MazeRunnerStack to ensure 
 * correct functionality.
 * @author andrew
 *
 */
public class TestStack {
	public static void main(String[] args) {
		boolean testsPassed = false;

		testsPassed = runTests();
		if(testsPassed) {
			System.out.println("Congrats! All Tests Passed.");
		}

	}


	/** Runs a battery of tests against MazeRunnerStack to ensure correct functionality
	 * @return Pass/Fail of All tests
	 */
	public static boolean runTests() {
		int testsPassedNum = 0;
		boolean testsPassedTotal = false;
		if(testEmptyPop()) {
			++testsPassedNum;
		}
		if(testPop2()) {
			++testsPassedNum;
		}
		if(testPush1()) {
			++testsPassedNum;
		}
		if(testPush2()) {
			++testsPassedNum;
		}
		if(testPush3()) {
			++testsPassedNum;
		}
		if(testPeek1()) {
			++testsPassedNum;
		}
		if(testPeek2()) {
			++testsPassedNum;
		}
		if(testIsEmpty()) {
			++testsPassedNum;
		}
		if(testIsEmpty2()) {
			++testsPassedNum;
		}
		if(testContains()) {
			++testsPassedNum;
		}
		if(testContains2()) {
			++testsPassedNum;
		}
		if(testQueue()) {
			++testsPassedNum;
		}
		if (testsPassedNum == 12) {
			testsPassedTotal = true;
		}
		return testsPassedTotal;
	}
	/** Tests to make sure popping an empty stack results in an Empty Stack Exception
	 * @return
	 */
	private static boolean testEmptyPop() {
		MazeRunnerStack testStack = new MazeRunnerStack();
		try {
			testStack.pop();
		} catch (EmptyStackException e) {
			return true;
		}
		catch(Exception e) {
			System.out.println("Test empty pop failed: expected EmptyStackException but got "+e);	
			return false;
		}
		return false;
	}
	/** checks to see if pop() is correctly working on a stack
	 * @return
	 */
	private static boolean testPop2() {
		nick p = new nick(4,5);
		nick[] pArray = new nick[10];
		pArray[0] = new nick(2,3);
		pArray[1] = new nick(6,4);
		pArray[2] = new nick(5,5);

		MazeRunnerStack testStack = new MazeRunnerStack(p,3,pArray);

		try {
			testStack.pop();
		}
		catch(EmptyStackException e) {
			System.out.println("EmptyStackException occured when calling pop()");
			return false;
		}
		if(testStack.peek().row == 6 && testStack.peek().col == 4) {
			return true;
		}
		else {
			System.out.println("Test pop failed: Expected Position node with 6,4 instead received: " 
					+testStack.peek().row+","+testStack.peek().col);
			return false;
		}	
	}
	/** Tests if push function correctly returns an error when trying to pass a null position
	 * @return
	 */
	private static boolean testPush1() {
		nick p = null;
		MazeRunnerStack testStack = new MazeRunnerStack();
		try {
			testStack.push(p);
		}
		catch(IllegalArgumentException e) {
			return true;
		}

		System.out.println("ERROR WHEN ATTEMPTING TO PUSH NULL POSITION TO STACK. "
				+ "EXPECTED ILLEGAL ARGUMENT EXCEPTION BUT INSTEAD CODE ALLOWED POSITION TO BE ADDED.");
		return false;
	}

	/** Tests if push function can correctly push an item to the stack with 100 items in it.
	 * @return
	 */
	private static boolean testPush2() {
		nick head = new nick(0,0);
		int stackSize = 75;
		nick[] array = new nick[100];

		nick p = new nick(0,5);
		nick p1 = new nick(0,8);
		nick p2 = new nick(3,5);
		nick p0 = new nick (0,10);
		boolean test = false;
		for(int i = 0; i < stackSize -1 ; ++i) {
			array[i] = new nick(0,0);
		}
		array[11] = p;
		array[71] = p1;
		array[64] = p2;
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);

		try {
			stack.push(p0);
		}
		catch(EmptyStackException e) {
			System.out.println("EmptyStackException occured when calling push()");
			return false;
		}
		if (stack.peek() != p0 && stack.peek() != null) {
			System.out.println("Error when pushing to a large stack. "
					+ "Head Object should be (0,10)"
					+ ",instead got: ("+stack.peek().row+","+stack.peek().col+").");
			test = false;	
		}
		for(int j = 0 ; j < 12; ++j) {
			stack.pop();
		}
		if (stack.peek() != p) {
			System.out.println("Error when pushing to a large stack. "
					+ "Position object at position 12 in array should be (0,5) "
					+ ",instead got: ("+stack.peek().row+","+stack.peek().col+").");
			test = false;	
		}

		else {
			test = true;
		}
		return test;
	}
	/** Tests when the ADT (Oversized Array) 
	 * is currently equal to the stack size, making sure it correctly expands
	 * @return
	 */
	private static boolean testPush3() {
		nick head = new nick(0,0);
		int stackSize = 10;
		int newStackSize = 0;
		nick[] array = new nick[10];
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);
		try {
			stack.push(new nick(0,1));
			for(int j = 0 ; j < stackSize +1 ; ++j) {
				++newStackSize;
			}

		}
		catch(Exception e) {
			System.out.println("Error: Push did not add expected space in oversized array.");
			return false;	
		}

		return true;

	}

	/** tests to see if peek is correctly returning null when peeking a null or empty stack
	 * @return
	 */
	private static boolean testPeek1() {
		MazeRunnerStack testStack = new MazeRunnerStack();
		try {
			testStack.peek();
		}
		catch (EmptyStackException e) {
			return true;
		}
		catch(Exception e) {
			System.out.println("Test empty peek failed: expected EmptyStackException but got "+e);	
			return false;
		}
		return false;
	}
	/** tests to see if peek correctly returns the top item of the stack
	 * @return
	 */
	private static boolean testPeek2() {
		nick head = new nick(0,0);
		int stackSize = 10;
		nick[] array = new nick[10];
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);
		try {
			stack.peek();
		}
		catch (EmptyStackException e) {
			System.out.println("EmptyStackException occured when calling peek()");
			return false;
		}
		for(int i = 0; i < stackSize -1 ; ++i) {
			stack.push(new nick(0,0));
		}
		stack.push(new nick(0,5));
		if(stack.peek() == null) {
			System.out.println("Error: Peek returned a null value.");
			return false;
		}
		if(stack.peek().row == 0 && stack.peek().col == 5) {
			return true;
		}
		else {
			System.out.println("Error calling peek(). Expected (0,5) but got:"
					+ "("+stack.peek().row+","+stack.peek().col+").");
			return false;
		}
	}
	// tests to see if IsEmpty() correctly returns false for a filled stack
	/**
	 * @return
	 */
	private static boolean testIsEmpty() {
		nick head = new nick(0,0);
		int stackSize = 10;
		nick[] array = new nick[10];
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);

		for(int i = 0; i < stackSize -1 ; ++i) {
			stack.push(new nick(0,0));
		}
		if(stack.isEmpty() == false) {
			return true;
		}
		else {
			System.out.println("Error checking isEmpty() on a non-empty stack.Expected true, "
					+ "but got: "+stack.isEmpty());
			return false;
		}
	}

	/** tests to see if IsEmpty() correctly returns true for an empty/null stack
	 * @return
	 */
	private static boolean testIsEmpty2() {
		MazeRunnerStack stack = new MazeRunnerStack(); 
		if(stack.isEmpty() == false) {
			System.out.println("Test IsEmpty2() failed: Expected true when called on an empty stack "
					+ "but got false");
			return false;
		}
		else {
			return true;
		}
	}

	/** tests to see if contains() correctly returns true for an position p that is in the stack
	 * @return
	 */
	private static boolean testContains() {
		nick head = new nick(0,0);
		int stackSize = 75;
		nick[] array = new nick[100];
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);
		nick p = new nick(0,5);
		nick p1 = new nick(0,8);
		nick p2 = new nick(3,5);
		nick p0 = new nick (0,10);
		boolean test = false;
		for(int i = 0; i < stackSize -1 ; ++i) {
			stack.push(new nick(0,0));
		}
		stack.push(p2);
		stack.push(p);
		stack.push(p1);


		if(stack.contains(p2) == true) {
			return true;
		}
		else {
			System.out.print("Test Contains() failed: Expected true when called on a position object that is "
					+ "in the stack, but got false.");
			return false;
		}
	}
	/** tests to see if contains() correctly returns false for a position not in the stack 
	 * and false for a null passed position
	 * @return
	 */
	public static boolean testContains2() {
		nick head = new nick(0,0);
		int stackSize = 10;
		nick[] array = new nick[100];
		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);
		nick p = new nick(0,5);
		nick p1 = new nick(0,8);
		nick p2 = new nick(3,5);
		nick p0 = new nick (0,10);
		nick pNull = null;
		boolean test = false;
		int testsFailed = 0;
		for(int i = 0; i < stackSize; ++i) {
			stack.push(new nick(0,0)) ;
		}
		stack.push(p);
		stack.push(p1);
		stack.push(p2);

		if(stack.contains(p0)) {
			System.out.print("Test Contains2() failed: Method incorrectly returned true when given "
					+ "a position not in the stack.") ;
			++testsFailed;
		}
		try {
			stack.contains(pNull);
		}
		catch(Exception e) {
			System.out.print("Test Contains2() failed: Exception thrown instead of false returned value "
					+ "when a null value is passed.");
			++testsFailed;
			return false;
		}
		return true;

	}
	/** Tests to make sure the MazeRunnerStack is not a queue implementation, but a stack implementation
	 * as expected.
	 * @return Pass/Fail Test
	 */
	public static boolean testQueue() {
		nick head = new nick(0,0);
		int stackSize = 4;
		nick[] array = new nick[6];
		array[0] = new nick(0,0);
		array[1] = new nick(0,0);
		array[2] = new nick(0,0);
		array[3] = new nick(0,0);

		MazeRunnerStack stack = new MazeRunnerStack(head,stackSize,array);
		nick p = new nick(2,5);
		stack.push(p);
		if(stack.peek() != null) {
			return false;
		}
		else if(stack.peek().row != 2 && stack.peek().col !=5) {
			for(int i = 0 ; i < stackSize-1 ; ++i) {
				stack.pop();
			}
			if(stack.peek().row == 2 && stack.peek().col == 5) {
				System.out.println("Error: Detected Queue implementation. This program is intended to be"
						+ " used with a stack implementation.");
				return false;
			}
			else {
				System.out.println("Error: Expected to find either 0,0 or 2,5 in the last position in the "+
						" stack but did not.");
				return false;
			}
		}

		return true;
	}


}
