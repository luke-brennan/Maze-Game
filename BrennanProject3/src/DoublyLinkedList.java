import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
	private class Node {
		private T value;
		private Node prev;
		private Node next;

		public Node(T value, Node prev, Node next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}

	private Node header;
	private Node trailer;
	private int size;

	public DoublyLinkedList() {
		header = new Node(null, null, null);
		trailer = new Node(null, header, null);
		header.next = trailer;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return header.next == trailer;
	}

	private void insertBetween(T newValue, Node node1, Node node2) {
		Node newNode = new Node(newValue, node1, node2);
		node1.next = newNode;
		node2.prev = newNode;
		size++;
	}

	private T removeBetween(Node node1, Node node2) {
		if (this.isEmpty()) {
			throw new IllegalStateException("Cannot remove from empty list");
		}
		T result = node1.next.value;

		node1.next = node2;
		node2.prev = node1;
		size--;

		return result;
	}

	public void insertAtHead(T newValue) {
		insertBetween(newValue, header, header.next);
	}

	public void addLast(T newValue) {
		insertBetween(newValue, trailer.prev, trailer);
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = header;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current.value;
		}
	}

	public boolean remove(T target) {
		Node current = header.next;
		while (current != trailer) {
			if (current.value.equals(target)) {
				removeBetween(current.prev, current.next);
				return true;
			}
			current = current.next;
		}
		return false;
	}

	public T removeHead() {
		return removeBetween(header, header.next.next);
	}

	public T removeTail() {
		return removeBetween(trailer.prev.prev, trailer);
	}

	public String toString() {
		if (this.isEmpty()) {
			return "List is empty";
		}
		String result = "";
		Node current = header.next;
		while (current != trailer) {
			result += current.value.toString() + " ";
			current = current.next;
		}
		return result;
	}

	public String reverseToString() {
		if (this.isEmpty()) {
			return "List is empty";
		}
		String result = "";
		Node current = trailer.prev;
		while (current != header) {
			result += current.value.toString() + " ";
			current = current.prev;
		}
		return result;
	}

	private class TWIterator implements Iterator<T> {

		private Node current;

		public TWIterator() {
			current = header.next;
		}

		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next != null;
		}

		public T next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException("No Elements");
			}

			T value = current.value;
			current = current.next;
			return value;
		}

		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return current.prev != null;
		}

		public T previous() {
			// TODO Auto-generated method stub
			if (hasPrevious() == false) {
				throw new NoSuchElementException("No Elements");
			}

			T value = current.value;
			current = current.prev;
			return value;
		}

	}

	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new TWIterator();
	}

}
