package invia;

import org.openqa.selenium.WebElement;

/**
 * Created by mirek on 7/30/2015.
 */
public class RuleFirstOrSecond extends Rule{
    int childOrder;

    public RuleFirstOrSecond(int childOrder) {
        this.childOrder = childOrder;
    }

    @Override
    public boolean matches(WebElement e) {
        String s = e.getText();
        s = s.replace('\n',' ');
        if (s.contains(childOrder+". dítě"))
            return true;
        return false;
    }
}
