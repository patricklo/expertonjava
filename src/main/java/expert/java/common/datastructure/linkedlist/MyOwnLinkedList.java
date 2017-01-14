package expert.java.common.datastructure.linkedlist;

/**
 * Created by Patrick on 14/1/17.
 */
public class MyOwnLinkedList {


    private LinkedListElement first;

    public MyOwnLinkedList(){
        first = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void insert(Object data){
        LinkedListElement element = new LinkedListElement(data);
        element.next = first;
        first = element;
    }

    public LinkedListElement delete(){
        if(first != null) {
            LinkedListElement temp = first;
            first = first.next;
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
