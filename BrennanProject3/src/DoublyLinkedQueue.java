
public class DoublyLinkedQueue<T> implements Queue<T> {

	private DoublyLinkedList<T> q;

	public DoublyLinkedQueue() {
		q = new DoublyLinkedList<T>();
	}

	@Override
	public void enqueue(T v) {
		// TODO Auto-generated method stub
		q.addLast(v);
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		if (q.size() == 0) {
			return null;
		}
		return q.removeHead();
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		return q.get(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return q.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return q.size() == 0;
	}

	public String toString() {
		return q.toString();
	}

}
