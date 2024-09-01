import java.util.Comparator;

public class SortedLinkedList {
    private  Container head;
    private  Container tail;
    private  int size;
    private Comparator<Container> c;

    public SortedLinkedList(Comparator<Container> c){
        this.c = c;
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }
    public  boolean isEmpty(){return size == 0;}

    public Container getHead(){return head;}
    public  Container getTail(){return tail;}
    public Comparator getComparator(){return c;}

    public void add(Container toAdd){
        if (isEmpty()) {
            head = toAdd;
            tail = head;
        }
        else if (c.compare(head,toAdd) >= 0) {
            toAdd.setNext(head);
            toAdd.getNext().setPrev(toAdd);
            head = toAdd;
        }
        else if (c.compare(head,toAdd) < 0) {
            Container current = head;
            while (current.getNext() != null && c.compare(current.getNext(),toAdd) < 0){
                current = current.getNext();
            }
            toAdd.setNext(current.getNext());
            if (current.getNext() != null) {
                current.getNext().setPrev(toAdd);
            } else {
                tail = toAdd;
            }
            current.setNext(toAdd);
            toAdd.setPrev(current);
        }
        size = size + 1;
    }

    public void addFirst (Container toAdd){
        if(isEmpty()){
            head = toAdd;
            tail = head;
        }
        else {
            toAdd.setNext(head);
            head = toAdd;
        }
        size = size + 1;
    }

    public void remove(Container toRemove){
        if (toRemove == head){
            Container newHead = toRemove.getNext();
            if (newHead != null){
                newHead.getPrev().setNext(null);
                newHead.setPrev(null);
                head = newHead;
            }
            else {
                tail = null;
            }
        }
        else if(toRemove == tail){
            tail = tail.getPrev();
            tail.setNext(null);
        }
        else{
            toRemove.getNext().setPrev(toRemove.getPrev());
            toRemove.getPrev().setNext(toRemove.getNext());
        }
        size = size - 1;
    }

    public String toString(){
        Container curr  = head;
        String ans = "";
        while(curr != null){
            if(curr.getNext() != null){
                ans += curr + " -> " ;
                curr = curr.getNext();
            }
            else {
                ans += curr;
                curr = curr.getNext();
            }
        }
        return ans;
    }

    public Container getMid(){
        int currSize = size/2;
        Container ans = head;
        while(currSize > 0){
            ans = ans.getNext();
            currSize -= 1;
        }
        return ans;
    }

    public Container getContainer(Point p){
        Container current = head;
        while (current != null && !current.getData().equals(p)){
            current = current.getNext();
        }
        return current;
    }
}