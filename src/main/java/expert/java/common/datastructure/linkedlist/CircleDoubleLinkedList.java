package expert.java.common.datastructure.linkedlist;

/**
 * Created by Patrick on 14/1/17.
 */
public class CircleDoubleLinkedList {




    private class CircleDoubleLinkedListElement {
        Object data;
        CircleDoubleLinkedListElement prev;
        CircleDoubleLinkedListElement next;

        public CircleDoubleLinkedListElement(Object data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }


        public void setPrev(CircleDoubleLinkedListElement prev) {
            this.prev = prev;
        }

        public void setNext(CircleDoubleLinkedListElement next) {
            this.next = next;
        }
    }
}
