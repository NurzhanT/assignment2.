import org.junit.Before;

interface CustomList<T> {
    void add(T item);
    T fetch(int index);
    int size();
}
class CustomArrayList<T> implements CustomList<T> {
    private Object[] items;
    private int size;

    public CustomArrayList() {
        items = new Object[10];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (size == items.length) {
            Object[] newArray = new Object[2 * items.length];
            System.arraycopy(items, 0, newArray, 0, items.length);
            items = newArray;
        }
        items[size++] = item;
    }

    @Override
    public T fetch(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) items[index];
    }

    @Override
    public int size() {
        return size;
    }
}
class CustomLinkedList<T> implements CustomList<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        if (head == null) {
            head = new Node(item, null, null);
            tail = head;
        } else {
            Node newNode = new Node(item, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public T fetch(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public int size() {
        return size;
    }
}
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CustomArrayListTest {

    private CustomArrayList<Integer> list;

    @Before
    public void setup() {
        list = new CustomArrayList<>();
    }

    @Test
    public void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testFetch() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2), list.fetch(1));
    }

    @Test
    public void testSize() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }
}

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new CustomLinkedList<>();
    }

    @Test
    public void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testFetch() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(Integer.valueOf(2), list.fetch(1));
    }

    @Test
    public void testSize() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }
}
