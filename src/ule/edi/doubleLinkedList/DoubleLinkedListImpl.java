package ule.edi.doubleLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleList<T> {


	//	referencia al primer aux de la lista
	private DoubleNode<T> front;

	//	referencia al Último aux de la lista
	private DoubleNode<T> last;


	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	
	///// ITERADOR normal //////////

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIterator<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIterator(DoubleNode<T> aux) {
			node = aux;
		}

		@Override
		public boolean hasNext() {
			// TODO
			return (node != null);
		}
	

		@Override
		public T next() {
		// TODO
			if(!hasNext()){
				throw new NoSuchElementException("There are no more elements");
			}
			T elem = node.elem;
			node = node.next;
			return elem;
		}
	}

	////// FIN ITERATOR

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		public DoubleLinkedListIteratorReverse(DoubleNode<T> aux) {
			// TODO	
			node = aux;
		}

		@Override
		public boolean hasNext() {
			// TODO	
			return (node != null);
			}

		@Override
		public T next() {
			// TODO
			if(!hasNext()){
				throw new NoSuchElementException("There are no more elements");
			}
			T elem = node.elem;
			node = node.prev;
			return elem;
		}
	}
	
	// TODO: añadir clases para el resto de iteradores

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgresive<T> implements Iterator<T> {
		DoubleNode<T> node;
		int count = 1;
		public DoubleLinkedListIteratorProgresive(DoubleNode<T> aux){
			node = aux;
		}

		@Override
		public boolean hasNext(){
			return (node != null);
		}

		@Override
		public T next(){
			if(!hasNext()){
				throw new NoSuchElementException("There are no more elements");
			}
			T elem = node.elem;
			for(int i = 0; i < count; i++){
				if(node != null){
					node = node.next;
				}
			}
			count++;
			return elem;
		}
	}

	@SuppressWarnings("hiding")
	private class DoubleLinkedListIteratorProgresiveReverse<T> implements Iterator<T> {
		DoubleNode<T> node;
		int count = 1;
		public DoubleLinkedListIteratorProgresiveReverse(DoubleNode<T> aux){
			node = aux;
		}

		@Override
		public boolean hasNext(){
			return (node != null);
		}

		@Override
		public T next(){
			if(!hasNext()){
				throw new NoSuchElementException("There are no more elements");
			}
			T elem = node.elem;
			for(int i = 0; i < count; i++){
				if(node != null){
					node = node.prev;
				}
			}
			count++;
			return elem;
		}
	}

	/////


	@SafeVarargs
	public DoubleLinkedListImpl(T...v ) {
		// permite añadir varios elementos a la lista en el constructor
		for (T elem:v) {
			this.addLast(elem);
		}
	}

	@Override
	public boolean isEmpty() {
		//TODO
		return (front == null);
	}

	@Override
	public void clear() {
		//TODO
		front = null;
		last = null;
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		DoubleNode<T> newNode = new DoubleNode<>(elem);
		if(front == null){
			front = newNode;
			last = newNode;
		}else{
			newNode.next = front;
			front.prev = newNode;
			front = newNode;
		}
	}

	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		DoubleNode<T> newNode = new DoubleNode<>(elem);
		if(front == null){
			addFirst(elem);
		}else{
			last.next = newNode;
			newNode.prev = last;
			last = newNode;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		if(position <= 0){
			throw new IllegalArgumentException("Position not valid");
		}
		if(position == 1){
			addFirst(elem);
		}else if(position > size()){
			addLast(elem);
		}else{
			DoubleNode<T> current = front;
			DoubleNode<T> newNode = new DoubleNode<>(elem);
			while(current != null && position > 1){
				current = current.next;
				position--;
			}
			newNode.next = current;
			newNode.prev = current.prev;
			current.prev.next = newNode;
			current.prev = newNode;
		}
	}

	@Override
	public T getElemPos(int position) {
		//TODO
		if(position < 1 || position > size()){
			throw new IllegalArgumentException("Position is not valid");
		}
		DoubleNode<T> current = front;
		for(int i = 0; i < position - 1; i++){
			current = current.next;
		}
		return current.elem;
	}

	@Override
	public int getPosFirst(T elem) {
		//TODO
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		DoubleNode<T> current = front;
		int position = -1;
		int currentPosition = 0;
		while(current != null){
			if(current.elem.equals(elem)){
				position = currentPosition;
				return position + 1;
			}
			current = current.next;
			currentPosition++;
		}
		if(position < 0){
			throw new NoSuchElementException("Element not in the list");
		}
		return position + 1;
	}

	@Override
	public int getPosLast(T elem) {
		//TODO
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		DoubleNode<T> current = front;
		int position = -1;
		int currentPosition = 0;
		while(current != null){
			if(current.elem.equals(elem)){
				position = currentPosition;
			}
			current = current.next;
			currentPosition++;
		}
		if(position < 0){
			throw new NoSuchElementException("Element not in the list");
		}
		return position + 1;
	}
	
	@Override
	public T removeLast()  throws EmptyCollectionException{
		//TODO
		if(front == null || last == null){
			throw new EmptyCollectionException("List is empty");
		}
		DoubleNode<T> removedNode = last;
		last = last.prev;
		return removedNode.elem;
	}

	@Override
	public T removePos(int pos)  throws EmptyCollectionException{
		// TODO
		if(front == null){
			throw new EmptyCollectionException("List is empty");
		}
		if(pos < 1 || pos > size()){
			throw new IllegalArgumentException("Position is not valid");
		}
		DoubleNode<T> current = front;
		for(int i = 0; i < pos - 1; i++){
			current = current.next;
		}
		if(pos > 1){
			if(current.next != null){
				current.next.prev = current.prev;
			}
			if(current.prev != null){
				current.prev.next = current.next;
			}
		}else{
			front = current.next;
			if(front != null){
				front.prev = null;
			}
		}
		return current.elem;
	}

	@Override
	public int removeN(T elem, int times) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		if(times < 1){
			throw new IllegalArgumentException("Times cant be less or equal to 0");
		}
		if(front == null){
			throw new EmptyCollectionException("List is empty");
		}
		int contador = 0;
		DoubleNode<T> current = front;
		while(current != null && times > 0){
			if(current.elem.equals(elem)){
				if(current.next != null){
					current.next.prev = current.prev;
				}
				if(current.prev != null){
					current.prev.next = current.next;
				}
				contador++;
				times--;
				if(front.elem.equals(elem)){
					front = front.next;
				}
			}
			current = current.next;
		}
		if(contador == 0){
			throw new NoSuchElementException("Element not in the list");
		}
		return contador;
	}

	@Override
	public DoubleList<T> copy() {
		// TODO Auto-generated method stub
		DoubleList<T> copiedList = new DoubleLinkedListImpl<>();
		DoubleNode<T> current = front;
		while(current != null){
			copiedList.addLast(current.elem);
			current = current.next;
		}
		return copiedList;
	}

	@Override
	public int size() {
		//TODO
		DoubleNode<T> current = front;
		int contador = 0;
		while(current != null){
			current = current.next;
			contador++;
		}
		return contador;
	}

	@Override
	public int maxRepeated() {
	// TODO
		DoubleNode<T> current = front;
		int repeated = 0;
		int maxRepeated = 0;
		while(current != null){
			repeated = countElem(current.elem);
			if(repeated > maxRepeated){
				maxRepeated = repeated;
			}
			current = current.next;
		}
		return maxRepeated;
	}

	@Override
	public void addAfter(T elem, T target) {
		// TODO Auto-generated method stub
		if(elem == null || target == null){
			throw new NullPointerException("Element and target cant be null");
		}
		if(countElem(target) == 0){
			throw new NoSuchElementException("Target not in the list");
		}
		DoubleNode<T> addedNode = new DoubleNode<>(elem);
		DoubleNode<T> current = front;
		boolean founded = false;
		while(current != null && !founded){
			if(current.elem.equals(target)){
				addedNode.next = current.next;
				addedNode.prev = current;
				current.next = addedNode;
				if(addedNode.next != null){
					addedNode.next.prev = addedNode;
				}
				founded = true;
			}
			current = current.next;
		}
		if(!founded){
			addLast(elem);
		}
	}

	@Override
	public void addAfterAll(T elem, T target) {
		// TODO Auto-generated method stub
		if(elem == null || target == null){
			throw new NullPointerException("Element and target cant be null");
		}
		DoubleNode<T> addedNode = new DoubleNode<>(elem);
		DoubleNode<T> current = front;
		int contador = countElem(target);
		if(contador == 0){
			addLast(elem);
		}else{
			while(current != null){
				if(current.elem.equals(elem)){
					contador--;
					if(contador == 0){
						addedNode.next = current.next;
						addedNode.prev = current;
						current.next = addedNode;
						addedNode.next.prev = addedNode;
					}
				}
				current = current.next;
			}
		}
	}

	@Override
	public T removePenul() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		if(front == null){
			throw new EmptyCollectionException("List is empty");
		}
		if(size() == 1){
			throw new NoSuchElementException("Element only has one element");
		}
		DoubleNode<T> penultNode = last.prev;
		penultNode.prev.next = penultNode.next;
		penultNode.next.prev = penultNode.prev;
		return penultNode.elem;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		if(elem == null){
			throw new NullPointerException("Element cant be null");
		}
		DoubleNode<T> current = front;
		int contador = 0;
		while(current != null){
			if(current.elem.equals(elem)){
				contador++;
			}
			current = current.next;
		}
		return contador;
	}

	@Override
	public boolean sameElems(DoubleList<T> other) {
		// TODO Auto-generated method stub
		if(other == null){
			throw new NullPointerException("List cant be null");
		}
		DoubleNode<T> current = front;
		boolean isSameElem = true;
		while(current != null){
			if(other.countElem(current.elem) < 1){
				isSameElem = false;
			}
			current = current.next;
		}
		return isSameElem;
	}

	@Override
	public String toString() {
		// TODO
		StringBuilder doubleLinkedList = new StringBuilder();
		doubleLinkedList.append("(");
		DoubleNode<T> current = front;
		while(current != null){
			doubleLinkedList.append(current.elem + " ");
			current = current.next;
		}
		doubleLinkedList.append(")");
		return doubleLinkedList.toString();
	}
	
	@Override
	public String toStringReverse() {
		// TODO
		StringBuilder doubleLinkedListReverse = new StringBuilder();
		doubleLinkedListReverse.append("(");
		DoubleNode<T> current = last;
		while(current != null){
			doubleLinkedListReverse.append(current.elem + " ");
			current = current.prev;
		}
		doubleLinkedListReverse.append(")");
		return doubleLinkedListReverse.toString();
	}

	@Override
	public String toStringFromUntil(int from, int until) {
		// TODO
		if(from <= 0 || until <= 0){
			throw new IllegalArgumentException("From and until cant be less or equal to 0");
		}
		StringBuilder doubleLinkedListFromUntil = new StringBuilder();
		DoubleNode<T> current = front;
		doubleLinkedListFromUntil.append("(");
		for(int i = 0; i < from - 1; i++){
			if(current != null){
				current = current.next;
			}
		}
		while(current != null && until >= from){
			doubleLinkedListFromUntil.append(current.elem + " ");
			current = current.next;
			until--;
		}
		doubleLinkedListFromUntil.append(")");
		return doubleLinkedListFromUntil.toString();
	}
	
	@Override
	public String toStringFromUntilReverse(int from, int until) {
		// TODO Auto-generated method stub
		if(from <= 0 || until <= 0){
			throw new IllegalArgumentException("From and until cant be less or equal to 0");
		}
		StringBuilder doubleLinkedListFromUntilReverse = new StringBuilder();
		DoubleNode<T> current = last;
		doubleLinkedListFromUntilReverse.append("(");
		for(int i = size(); i > from; i--){
			current = current.prev;
		}
		while(current != null && until <= from){
			doubleLinkedListFromUntilReverse.append(current.elem);
			current = current.prev;
			until++;
		}
		doubleLinkedListFromUntilReverse.append(")");
		return doubleLinkedListFromUntilReverse.toString();
	}


	@Override
	public Iterator<T> iterator() {
		return new DoubleLinkedListIterator<>(front);
	}

	@Override
	public Iterator<T> reverseIterator() {
		return new DoubleLinkedListIteratorReverse<>(last);
	}
	
	@Override
	public Iterator<T> progressIterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListIteratorProgresive<>(front);
	}

	@Override
	public Iterator<T> progressReverseIterator() {
		// TODO Auto-generated method stub
		return new DoubleLinkedListIteratorProgresiveReverse<>(last);
	}
}