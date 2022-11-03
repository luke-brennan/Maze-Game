
public class DoublyLinkedStack<T> implements Stack<T> {

	private DoublyLinkedList<T> stack;

	public DoublyLinkedStack() {
		stack = new DoublyLinkedList<>();
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		return stack.removeTail();
	}

	@Override
	public void push(T o) {
		// TODO Auto-generated method stub
		stack.addLast(o);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return stack.size() == 0;
	}
}