package invia;

import org.openqa.selenium.WebElement;

/**
 * Created by mirek on 7/30/2015.
 */
public class RuleRodin extends Rule{
    @Override
    public boolean matches(WebElement e) {
        String s = e.getText();
        s = s.replace('\n',' ');
        if (s.contains("2 dospě"))
            return true;
        return false;
    }
}
