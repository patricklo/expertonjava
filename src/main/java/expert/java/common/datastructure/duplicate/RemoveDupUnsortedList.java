package expert.java.common.datastructure.duplicate;

import java.util.LinkedList;

/**
 * Created by patrick on 6/16/2015.
 */
public class RemoveDupUnsortedList {

    public static void main(String[] args){
        LinkedList unsorted = new LinkedList();
        unsorted.push(20);
        unsorted.push(13);
        unsorted.push(13);
        unsorted.push(11);
        unsorted.push(11);
        unsorted.push(11);
        unsorted.push(10);
        unsorted.push(10);

        removeDuplicates(unsorted);
    }
    public static void removeDuplicates(LinkedList unsorted){

    }

}

class Node {
    Node left;
    Node right;
    int data;

    Node(int newData) {
        left = null;
        right = null;
        data = newData;
    }
}