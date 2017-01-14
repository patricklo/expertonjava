package expert.java.common.datastructure.linkedlist;

/**
 * Created by Patrick on 14/1/17.
 */
public class DoubleLinkedList {

    private DoubleLinkedListElement firstElement;
    private DoubleLinkedListElement lastElement;

    public DoubleLinkedList(){
        firstElement = null;
        lastElement = null;
    }

    public boolean isEmpty(){
        return firstElement == null;
    }

    public void insert(Object value){

        DoubleLinkedListElement newElement = new DoubleLinkedListElement(value);
        if(isEmpty()){
            firstElement = newElement;
            lastElement = newElement;
        }else{
            firstElement.prev = newElement;
            newElement.next = firstElement;
            newElement.prev = null;
            firstElement = newElement;
        }
    }

    public DoubleLinkedListElement delete(){
        if(!(isEmpty())){
            DoubleLinkedListElement temp = firstElement;
            if(firstElement.next == null){
                firstElement.next= null;
                firstElement.prev = null;
            }else {
                firstElement = firstElement.next;
                firstElement.prev = null;
            }
            return temp;
        }
        return null;
    }



    private class DoubleLinkedListElement {
        Object data;
        DoubleLinkedListElement prev;
        DoubleLinkedListElement next;

        public DoubleLinkedListElement(Object data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }


        public void setPrev(DoubleLinkedListElement prev) {
            this.prev = prev;
        }

        public void setNext(DoubleLinkedListElement next) {
            this.next = next;
        }
    }
}
