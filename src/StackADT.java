import java.util.EmptyStackException;

/** A interface used to allow implementation of MazeRunnerStack
 * @author andrew
 *
 * @param <E> placeholder that allows for any type to be substituted in
 */
public interface StackADT<E> {



	public void push(E item); // adds a new item to the top of the stack

	public E pop() throws EmptyStackException; // removes the top item from the stack and returns it

	public E peek() throws EmptyStackException; // returns the top item from the stack without removing it

	public boolean isEmpty(); // returns true if the stack is empty, otherwise returns false


}
