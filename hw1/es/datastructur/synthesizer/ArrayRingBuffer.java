package es.datastructur.synthesizer;
import java.util.Iterator;



public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    public int fillCount(){
        return fillCount;
    }
    @Override
    public int capacity() {
        return rb.length;
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (isFull()) {
            throw new RuntimeException("Ring buffer underlow");

        }
        else {
            rb[last] = x;
            last += 1;
            fillCount ++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer overflow");

        }
        else {
            T item = rb[first];
            rb[first] = null;
            first = (first + 1) % capacity();   //cuz it is a ring array
            fillCount--;
            return item;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

       if (isEmpty()){
           throw new RuntimeException("Ring buffer underflow");

       }
       else {
           return rb[first];
       }
    }

    @Override
    public Iterator<T> iterator() {
        return  new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T>{

        private int wizPos;
        public ArrayRingBufferIterator() { wizPos = first; }

        @Override
        public boolean hasNext() {
            return wizPos< fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos = (wizPos+1) % rb.length;
            return returnItem;
        }
    }
    public boolean equals(Object other){
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount() != o.fillCount) {
            return false;
        }
        for (int i = first; i != last; i = (i+1) % rb.length) {
            if (rb[i] != o.rb[i]) {
                return false;
            }
        }
        return true;
    }
}

