package invia;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static State state = new State();
    static WebDriver driver;
    private static String detailPage;
    private static int pageNum;

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "w:\\Code\\Invia\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://last-minute.invia.cz/?nl_country_id%5B%5D=33&nl_country_id%5B%5D=22&nl_country_id%5B%5D=28&nl_country_id%5B%5D=30&nl_country_id%5B%5D=33&nl_country_id%5B%5D=22&nl_country_id%5B%5D=28&nl_country_id%5B%5D=30&d_start_from=08.08.2015&d_end_to=31.08.2015&nl_length_int%5B%5D=6%7C9&nl_length_int%5B%5D=10%7C12&nl_stars%5B%5D=4&nl_stars%5B%5D=5&nl_meal_id%5B%5D=5&nl_meal_id%5B%5D=11&nl_transportation_id%5B%5D=3_1&nl_category_id%5B%5D=11&nl_type_id=3&sort=c_price");

        WebElement sorter = driver.findElement(By.cssSelector("p.search-results-sort-nav"));
        WebElement link = sorter.findElement(By.xpath(".//a[span[contains(text(),'nejlevnějšího')]]"));
        link.click();

        Thread.sleep(10000);


        int i = 5;
        pageNum = 2;
        do {
            driver.get("http://last-minute.invia.cz/?nl_country_id%5B%5D=33&nl_country_id%5B%5D=22&nl_country_id%5B%5D=28&nl_country_id%5B%5D=30&d_start_from=08.08.2015&d_end_to=31.08.2015&nl_length_int%5B%5D=6%7C9&nl_length_int%5B%5D=10%7C12&nl_stars%5B%5D=4&nl_stars%5B%5D=5&nl_meal_id%5B%5D=2&nl_meal_id%5B%5D=1&nl_transportation_id%5B%5D=3_1&nl_category_id%5B%5D=11&c_price_charges_from=&c_price_charges_to=&nl_distance_beach=-1&nd_review_rating_average=-1&s_hotel_invia=&nl_hotel_id_autocomplete=&nl_tour_id=&s_fulltext=&nd_price_discount_from=&nd_price_discount_to=&nl_type_id=3&sort=c_price");

            if (pageNum > 1) {
                WebElement nextPage = null;
                while (true){
                    List<WebElement> scrollers = driver.findElements(By.xpath("//a[@data-page='" + pageNum + "']"));
                    if (scrollers.size() > 0) {
                        break;
                    }

                    List<WebElement> elements;
                    try {
                        elements = driver.findElements(By.xpath("//a[@data-page]"));
                        nextPage = elements.get(elements.size()-1);
                        Thread.sleep(3000);
                        scrollTo(nextPage);
                        nextPage.click();
                    } catch (Exception e) {
                        System.err.println("Retry");
                        Thread.sleep(10000);
                        elements = driver.findElements(By.xpath("//a[@data-page]"));
                        nextPage = elements.get(elements.size()-1);
                        scrollTo(nextPage);
                        nextPage.click();
                    }
                    Thread.sleep(2000);
                }
                Thread.sleep(2000);
                try {
                    nextPage = driver.findElement(By.xpath("//a[@data-page='" + pageNum + "']"));
                    scrollTo(nextPage);
                    nextPage.click();
                } catch (Exception e) {
                    System.err.println("Retry2");
                    Thread.sleep(5000);
                    nextPage = driver.findElement(By.xpath("//a[@data-page='" + pageNum + "']"));
                    scrollTo(nextPage);
                    nextPage.click();
                }
                Thread.sleep(5000);
                WebElement top = driver.findElement(By.id("menu-breadcrumb"));
                scrollTo(top);
            }

            List<WebElement> elements = driver.findElements(By.cssSelector("#main li.hotel-box div.head h2.title > a"));

            WebElement e = elements.get(i);
            scrollTo(e);
            try {
                detailPage = e.getAttribute("href");
                System.out.println("Link (" + "page:" + pageNum + " hotel:" + i + "): " + detailPage);

                e.click();
            } catch (Exception e1) {
                System.err.println("Problem - going unusual way:"+e1.getClass()+" "+e1.getMessage());
                Thread.sleep(10000);
                elements = driver.findElements(By.cssSelector("#main li.hotel-box div.head h2.title > a"));
                e = elements.get(i);
                scrollTo(e);

                detailPage = e.getAttribute("href");
                System.out.println("Link (" + "page:" + pageNum + " hotel:" + i + "): " + detailPage);
                e.click();
            }


            try {
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                Thread.sleep(3000);

                //debug
//            driver.get("http://hotel.invia.cz/recko/kos/mitsis-family-village/tour-896693/?id=58382405&airport%5B0%5D=1&airport%5B1%5D=4&utm_expid=97092943-15.a85iyr5OSD-_-EYaHGKZNA.0&utm_referrer=http%3A%2F%2Flast-minute.invia.cz%2F%3Fnl_country_id%255B%255D%3D33%26nl_country_id%255B%255D%3D22%26nl_country_id%255B%255D%3D28%26nl_country_id%255B%255D%3D30%26nl_country_id%255B%255D%3D31%26nl_country_id%255B%255D%3D33%26nl_country_id%255B%255D%3D22%26nl_country_id%255B%255D%3D28%26nl_country_id%255B%255D%3D30%26nl_country_id%255B%255D%3D31%26d_start_from%3D23.07.2015%26d_end_to%3D31.08.2015%26nl_length_int%255B%255D%3D6%257C9%26nl_stars%255B%255D%3D4%26nl_stars%255B%255D%3D5%26nl_meal_id%255B%255D%3D5%26nl_meal_id%255B%255D%3D11%26nl_transportation_id%255B%255D%3D3_1%26nl_transportation_id%255B%255D%3D3_4%26nl_category_id%255B%255D%3D11%26nl_type_id%3D3");
//            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//            Thread.sleep(3000);

                //detail page
                WebElement jumpButton = driver.findElement(By.xpath("//a[@href='#order-form']"));
                jumpButton.click();

                List<WebElement> extras = driver.findElements(By.xpath("//a[@href='#' and @class='btn' and contains(text(),'Doplňkové ceny')]"));
                for (WebElement extra : extras) {
                    Thread.sleep(500);
                    if (!extra.isDisplayed())
                        Thread.sleep(2000);
                    extra.click();
                }

                boolean sold = false;
                try {
                    WebElement excl = driver.findElement(By.cssSelector("div.ico-exclamation"));
                    sold = excl.getText().contains("vyprodán");
                } catch (NoSuchElementException e1) {
                    // sold element is not present
                }
                if (!sold)
                    orderHotelIfPossible();
            } catch (Exception e1) {
                System.err.println("ERROR:" + detailPage);
                e1.printStackTrace();
            }
            state.reset();
            i++;

            if (i == 8) {
                i = 0;
                pageNum++;
            }

        } while (true);


    }

    private static void orderHotelIfPossible() throws InterruptedException {
        WebElement form = driver.findElement(By.id("order_form_elem"));
        WebElement wrapper = form.findElement(By.cssSelector("div.calculation-wrapper"));


        //scan form
        List<WebElement> tables = wrapper.findElements(By.xpath(".//table"));
        for (WebElement table : tables) {
//                System.out.println("Searching table ...");
            if (!state.isAdultOk()) {
                WebElement room = findAdults(table);
                if (room != null) {
                    state.adultsBooked(room.getText());
                    bookRoom(driver, room, 2);
                    continue;
                }
            }

            if (!state.isChildOk())
                findChild(table);

            if (state.isOk())
                break;
        }

        if (state.isOk()) {
            WebElement sum = driver.findElement(By.cssSelector("p.sum-price"));
            String sumPrice = sum.findElement(By.xpath(".//span[@class='price']")).getText();
            WebElement hotel = driver.findElement(By.id("product-detail"));
            String name = hotel.findElement(By.xpath(".//h2")).getText();
            String location = hotel.findElement(By.xpath(".//p[@class='annot']")).getText();
            location = location.replace("(Zobrazit na mapě)", "");
            String status = "OUTPUT:";

            String childRooms = state.string6 + state.string3;
            if (childRooms.contains("1.") && !childRooms.contains("2."))
                status = "WARNING";

            System.out.println(status + " | " + sumPrice.replace("\n","") + " | " + state.string6.replace("\n","") + " | " + state.string3.replace("\n","") + " | " + state.stringAdult.replace("\n","") + " | " + location + " | " + name.replace("\n","") + " | " + detailPage);
        } else {
            System.out.println("NOT ACCEPTABLE " + detailPage);
        }
    }

    private static void bookRoom(WebDriver driver, WebElement room, int people) throws InterruptedException {
//        System.out.println("Room:"+room.getText());
        WebElement row = findParent(room, "tr");
//        System.out.println("Row:"+row.getText());

        WebElement option2 = row.findElement(By.xpath("td[select]/a"));

//        System.out.println("Option:" + option2.getText());
        scrollTo(option2);
        option2.click();

        List<WebElement> selectDivs = driver.findElements(By.cssSelector("div.selectBox-dropdown-menu"));
        for (WebElement selectDiv : selectDivs) {
            if (!selectDiv.isDisplayed())
                continue;
//            System.out.println("Select:" + selectDiv.getText());
            WebElement item = selectDiv.findElement(By.xpath("ul/li/a[@rel='" + people + "']"));
//            System.out.println("Item:" + item.getText());
            item.click();
            break;
        }
    }

    private static WebElement findAdults(WebElement table) {
//        System.out.println(" .. Find adults");
        List<WebElement> rooms = table.findElements(By.xpath(".//label[//strong[contains(text(),'Dosp')]]"));
        WebElement room = findContains(rooms, "Rodin");
        if (room == null)
            room = findContains(rooms, "Dosp");
        return room;
    }


    private static final Pattern DO_LET = Pattern.compile(".*do ([0-9]*) let.*");
    private static final Pattern OD_DO_LET = Pattern.compile(".* [0-9]*-([0-9]*) let.*");

    private static void findChild(WebElement table) throws InterruptedException {
//        System.out.println("  .. Find child");
        List<WebElement> rooms = table.findElements(By.xpath(".//label[strong[contains(text(),'dítě')]]"));
        rooms = Rule.sortFirst(rooms);
        findAndBookOneChild(rooms);
        rooms = Rule.sortSecond(rooms);
        findAndBookOneChild(rooms);
    }

    private static void findAndBookOneChild(List<WebElement> rooms) throws InterruptedException {
        for (WebElement r : rooms) {
            String text = r.getText();
            text = text.replace('\n', ' ');
//            System.out.println("Child room:" + text);
            Matcher matcher = DO_LET.matcher(text);
            if (matcher.matches()) {
                String limit = matcher.group(1);
//                System.out.println("**Child: limit:"+limit);
                if (state.childOk(limit, text)) {
                    bookRoom(driver, r, 1);
                    break;
                }
            }
            matcher = OD_DO_LET.matcher(text);
            if (matcher.matches()) {
                String limit = matcher.group(1);
//                System.out.println("**Child: limit:"+limit);
                if (state.childOk(limit, text)) {
                    bookRoom(driver, r, 1);
                    break;
                }
            }
        }
    }

    public static WebElement findContains(List<WebElement> list, String what) {
        for (WebElement i : list) {
//            System.out.println("   .... Searching for "+what+" in "+i.getText());
            if (i.getText().contains(what))
                return i;
        }
        return null;
    }

    public static WebElement findParent(WebElement e, String tag) {
        WebElement parent = e;
        do {
            parent = parent.findElement(By.xpath(".."));
            if (tag.equals(parent.getTagName()))
                return parent;
        } while (parent != null);
        return e.findElement(By.xpath(".."));
    }

    public static void scrollTo(WebElement e) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        Thread.sleep(500);
    }
}


//                WebElement option2 = row.findElement(By.xpath("td[select]/a/span[@class='selectBox-label']"));
//                System.out.println("Option:"+option2.getText());
//                option2.click();

//                WebElement option2 = row.findElement(By.xpath("td/select"));
//                Select select = new Select(option2);
//                System.out.println("Option:" + select.toString());
//                select.selectByVisibleText("2");

//                WebDriverWait wait = new WebDriverWait(driver, 30);
//                wait.until(ExpectedConditions.elementToBeClickable(By.id(option2.getAttribute("id"))));
