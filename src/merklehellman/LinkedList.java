/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merklehellman;

/**
 * LinkedList class contains a head node and its number of nodes as listCount.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 09/16/2015
 * @param <E>
 */
public class LinkedList<E> {

    private Node<E> head;
    private int listCount;

    /**
     * Constructor with no argument
     */
    public LinkedList() {
        head = null;
        listCount = 0;
    }

    /**
     * precondition: null postcondition: this method will add a new Node with
     * data at the end of the LinkedList.
     *
     * @param data is the new added node's data.
     * Big-Theta(N) for any cases
     */
    public void add(E data) {
        Node<E> tmp = new Node(data);
        Node<E> current = head;
        if (head == null) {
            head = new Node(data);
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(tmp);
        }

        listCount++;
    }

    /**
     * precondition:null postcondition: this metho will count and return the
     * number of nodes in the LinkedList
     *
     * @return listCount.
     * Big-Theta(1) for any cases
     */
    public int size() {
        return listCount;
    }

    /**
     * precondition: index < listCount postcondition: this method will find and
     * return the data at the specified index of the LinkedList. 
     * @param index find the node with index 
     * @return the data of the node.
     * Big-Theta(N) for any cases
     */
    public E get(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

}
