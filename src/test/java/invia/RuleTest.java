package invia;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mirek on 7/31/2015.
 */
public class RuleTest {

    @Test
    public void testRule(){
        List<WebElement> l = new LinkedList<WebElement>(){
            {
                add(new WebE("1. dítě 2-12 let \n" +
                        "Dvoulůžkový pokoj (1 dospělých + 1 dětí)"));
                add(new WebE("1. dítě 2-12 let \n" +
                        "Dvoulůžkový pokoj (2 dospělých + 1 dětí)"));
                add(new WebE("1. dítě do 1 roku \n" +
                        "Dvoulůžkový pokoj (1 dospělých + 1 dětí), Dvoulůžkový pokoj (2 dospělých... více"));
                add(new WebE("2. dítě do 1 roku \n" +
                        "Dvoulůžkový pokoj (2 dospělých + 2 dětí)"));
                add(new WebE("2. dítě 2-12 let \n" +
                        "Dvoulůžkový pokoj (2 dospělých + 2 dětí)"));
                add(new WebE("2. dítě 2-12 let \n" +
                        "Dvoulůžkový pokoj (2 dospělých + 2 dětí)"));
                add(new WebE("krava"));
                add(new WebE("nazdar"));
            }
        };

        System.out.println("First");
        l = Rule.sortFirst(l);
        for (WebElement webElement : l) {
            System.out.println(webElement.getText());
        }
        System.out.println("Second");
        l = Rule.sortSecond(l);
        for (WebElement webElement : l) {
            System.out.println(webElement.getText());
        }
    }


    private static final class WebE implements WebElement{

        @Override
        public String toString() {
            return text;
        }

        String text;

        public WebE(String text) {
            this.text = text;
        }

        @Override
        public void click() {

        }

        @Override
        public void submit() {

        }

        @Override
        public void sendKeys(CharSequence... keysToSend) {

        }

        @Override
        public void clear() {

        }

        @Override
        public String getTagName() {
            return null;
        }

        @Override
        public String getAttribute(String name) {
            return null;
        }

        @Override
        public boolean isSelected() {
            return false;
        }

        @Override
        public boolean isEnabled() {
            return false;
        }

        @Override
        public String getText() {
            return text;
        }

        @Override
        public List<WebElement> findElements(By by) {
            return null;
        }

        @Override
        public WebElement findElement(By by) {
            return null;
        }

        @Override
        public boolean isDisplayed() {
            return false;
        }

        @Override
        public Point getLocation() {
            return null;
        }

        @Override
        public Dimension getSize() {
            return null;
        }

        @Override
        public String getCssValue(String propertyName) {
            return null;
        }
    }
}
