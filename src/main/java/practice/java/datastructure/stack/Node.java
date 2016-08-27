package practice.java.datastructure.stack;

import java.util.EmptyStackException;

/**
 * Created by patrick on 12/31/14.
 */
public class Node {
    int data;
    Node next;
    Node elementData[] = new Node[100];
    protected int elementCount = 0;

    public void push(int newData){
        Node newNode = new Node();
        newNode.data = newData;
        elementData[elementCount++] = newNode;
    }

    public int pop(){
        int len = size();
        Node node = peek();
        removeElementAt(len-1);
        return node.data;
    }

    public synchronized void removeElementAt(int index) {
        if (index >= elementCount) {
            throw new ArrayIndexOutOfBoundsException(index + " >= " +
                    elementCount);
        }
        else if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        elementCount--;
        elementData[elementCount]=null;
    }

    public synchronized Node peek() {
        int len = size();
        if (len == 0)
            throw new EmptyStackException();
        return elementData[len - 1];
    }
    public synchronized int size() {
        return elementCount;
    }



}
