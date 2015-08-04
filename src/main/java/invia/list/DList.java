package invia.list;

import java.util.List;

/**
 * Created by mirek on 7/31/2015.
 */
public class DList<T> {
    public ListNode<T> first;
    public ListNode<T> last;

    public DList() {
    }
    public void addLast(T item){
        ListNode<T> newN = new ListNode<T>(last,null,item);
        if (last == null){
            first = newN;
        }
        last = newN;
    }
    public void addAll(List<T> all){
        for (T t : all) {
            addLast(t);
        }
    }
    public void addBefore(ListNode<T> node, T item){
        ListNode<T> newE = new ListNode<T>(node.prev, node, item);
        if (node.prev != null)
            node.prev.next = newE;
        else
            first = newE;
        node.prev = newE;
    }
}
