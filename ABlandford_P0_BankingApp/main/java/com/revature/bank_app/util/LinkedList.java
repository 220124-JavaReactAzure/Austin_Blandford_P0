package com.revature.bank_app.util;

public class LinkedList<T> implements List<T> {
	
	private int size;
	private Node<T> head;
	private Node<T> tail;

	@Override
	public boolean add(T element) {
		
		if(element == null) {
			return false;
		}
		
		Node<T> newNode = new Node<>(element);
		if(head == null) {
			tail = head = newNode;		
		} else {
			tail.nextNode = newNode;
			tail = newNode;
		}
		size++;
		
		return false;
	}

	@Override
	public boolean contains(T element) {
		
		Node<T> runner = head;
		
		while(runner != null) {
			if(runner.data.equals(element)) {
				return true;
			}
			
			runner = runner.nextNode;
		}
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean remove(T element) {
		Node<T> prevNode = null;
		Node<T> currentNode = head;
		if (size == 0) {
            return false;
        }

        for (int i = 0; i < size; i++) {

            if ((currentNode.data == null && element == null) || (currentNode.data != null && currentNode.data.equals(element))) {

                if (currentNode == head) {
                    head = currentNode.nextNode;
                } else {
                    prevNode.nextNode = currentNode.nextNode;
                }

                size--;
                return true;
            }

            prevNode = currentNode;
            currentNode = currentNode.nextNode;
        }

        return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override 
	public T get(int index) {
		Node<T> runner = head;
		int count = 0;
		
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		while(runner != null) {
			if(count == index) {
				return runner.data;
			}
			
			count++;
			runner = runner.nextNode;
		}
		
		return null;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	private static class Node<T>{
		T data;
		Node<T> nextNode;
		
		public Node(T data) {
			this.data = data;
		}
	}

}