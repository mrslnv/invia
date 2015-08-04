package invia.list;

/**
 * Created by mirek on 7/31/2015.
 */
public class ListNode<T> {
    public ListNode<T> prev;
    public ListNode<T> next;
    public T item;

    public ListNode(T item) {
        this.item = item;
    }

    public ListNode(ListNode<T> prev, ListNode<T> next, T item) {
        this.prev = prev;
        this.next = next;
        this.item = item;
    }
}
