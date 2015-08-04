package invia;

import org.openqa.selenium.WebElement;

/**
 * Created by mirek on 7/30/2015.
 */
public class Rule2plus2 extends Rule{
    @Override
    public boolean matches(WebElement e) {
        String s = e.getText();
        s = s.replace('\n',' ');
        if (s.contains("2 dospělých + 2 dětí"))
            return true;
        return false;
    }
}
