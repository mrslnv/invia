package invia;

import invia.list.DList;
import invia.list.ListNode;
import org.openqa.selenium.WebElement;

import java.util.*;

public abstract class Rule {
    public static final List<Rule> RULES_COMMON = new LinkedList<Rule>(){
        {
            add(new Rule2plus2());
            add(new Rule2Dosp());
            add(new RuleRodin());
            add(new RulePristylka());
        }
    };
    public static final List<Rule> RULES_FIRST = new LinkedList<Rule>();
    public static final List<Rule> RULES_SECOND = new LinkedList<Rule>();

    static{
        RULES_FIRST.add(new RuleFirstOrSecond(1));
        RULES_FIRST.addAll(RULES_COMMON);

        RULES_SECOND.add(new RuleFirstOrSecond(2));
        RULES_SECOND.addAll(RULES_COMMON);
    }


    public abstract boolean matches(WebElement e);

    public static List<WebElement> sortFirst(List<WebElement> rooms){
        return sort(rooms,Rule.RULES_FIRST);
    }
    public static List<WebElement> sortSecond(List<WebElement> rooms){
        return sort(rooms,Rule.RULES_SECOND);
    }

    public static List<WebElement> sort(List<WebElement> rooms,List<Rule> rules){
        LinkedList<WebElement> init = new LinkedList<WebElement>();
        init.addAll(rooms);

        DList<LinkedList<WebElement>> in = new DList<LinkedList<WebElement>>();
        in.addLast(init);

        for (Rule r: rules){
            // go through list of lists
            ListNode<LinkedList<WebElement>> listNode = in.first;
            while (listNode != null){
                //apply rule and split to 2 list (priority, the rest)
                LinkedList<WebElement> list =  listNode.item;
                // make no sense to sortFirst {one item}
                if (list.size() == 1) {
                    listNode = listNode.next;
                    continue;
                }

                LinkedList<WebElement> listWithPriority = new LinkedList<WebElement>();
                Iterator<WebElement> it = list.iterator();
                while (it.hasNext()) {
                    WebElement e = it.next();
                    if (r.matches(e)){
                        listWithPriority.add(e);
                        it.remove();
                    }
                }
                if (!listWithPriority.isEmpty()){
                    in.addBefore(listNode,listWithPriority);
                }

                // go through list of lists
                listNode = listNode.next;
            }

        }
        ArrayList<WebElement> ret = new ArrayList<WebElement>(rooms.size());
        ListNode<LinkedList<WebElement>> listNode = in.first;
        while (listNode != null){
            ret.addAll(listNode.item);
            listNode = listNode.next;
        }

        return ret;
    }
}
