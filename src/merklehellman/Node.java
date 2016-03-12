/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package merklehellman;

/**
 * Node class can create any type of node that has a data and a pointer to the
 * next node.
 *
 * @author ruijieouyang
 * @version 1.0
 * @since 09/16/2015
 * @param <E>
 */
public class Node<E> {

    private E data;
    private Node<E> next;

    /**
     * Constructor with no argument
     */
    public Node() {

    }

    /**
     * Constructor can creates a node with data, and the next pointer is null
     *
     * @param data is the data of the node
     */
    public Node(E data) {
        this.data = data;
        next = null;
    }

    /**
     * Constructor can creates a node with data and pointer to next node, and
     * the next pointer is not null
     *
     * @param data
     * @param next
     */
    public Node(E data, Node<E> next) {
        this.next = next;
        this.data = data;
    }

    /**
     * precondition:null
     * postcondition:this method will return the node's data.
     * @return the node's data.
     * Big-Theta(1) for any cases
     */
    public E getData() {
        return data;
    }

    /**
     * precondition:null
     * postcondition:this method will set the node's data.
     * @param data set the node's data.
     * Big-Theta(1) for any cases
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * precondition:null
     * postcondition:this method will get the next node.
     * @return the next node
     * Big-Theta(1) for any cases
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * precondition:null
     * postcondition:this method will return the next node
     * @param next set this node point to the 'next' node
     * Big-Theta(1) for any cases
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

}
