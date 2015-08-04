package invia;

import org.openqa.selenium.WebElement;

/**
 * Created by mirek on 7/30/2015.
 */
public class RulePristylka extends Rule{

    @Override
    public boolean matches(WebElement e) {
        String s = e.getText();
        s = s.replace('\n',' ');
        if (s.contains("přistýl"))
            return true;
        return false;
    }
}
