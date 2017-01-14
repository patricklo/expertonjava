package expert.java.common.datastructure.linkedlist;

/**
 * Created by Patrick on 14/1/17.
 */
public class SingleLinkedList {


    private LinkedListElement current;

    public SingleLinkedList(){
        current = null;
    }

    public boolean isEmpty(){
        return current == null;
    }

    public void insert(Object data){
        LinkedListElement element = new LinkedListElement(data);
        element.next = current;
        current = element;
    }

    public LinkedListElement delete(){
        if(current != null) {
            LinkedListElement temp = current;
            current = current.next;
            return temp;
        }else{
            return null;
        }
    }




    private class LinkedListElement{
        // reference to the next node in the chain, or null if there isn't one.
        LinkedListElement next;

        // data carried by this node. could be of any type you need.
        Object data;

        // Node constructor
        public LinkedListElement(Object dataValue) {
            next = null;
            data = dataValue;
        }

        public void setNext(LinkedListElement next) {
            this.next = next;
        }
    }
}
