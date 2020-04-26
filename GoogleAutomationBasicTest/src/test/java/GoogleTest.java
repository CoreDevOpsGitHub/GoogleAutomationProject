import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;


public class GoogleTest {
    public static void main(String[] args) {


        //----------------------------------------------------------------------------------------

        /*
        HELLO THERE!!! Thank you the opportunity to submit this code.
         Please note that you will need to change th path in order for this to run
         If you have any questions please feel free to call directly at 913-850-0248
        */

        //----------------------------------------------------------------------------------------


        //This sets the Driver Properties !!!!Change path here!!!!!
        System.setProperty("webdriver.chrome.driver", "!!!PATH!!!/GoogleAutomationBasicTest/src/test/resources/driver/chromedriver");
        WebDriver driver = new ChromeDriver();


        //This is the URL that we will open
        String url = "http://www.google.com";
        String expectedValidationPageOpen = "Google";
        String title = "";

        //This open the URL
        driver.get(url);

        //Validation Page Open (This confirms the tab states "Google")
        Assert.assertEquals(expectedValidationPageOpen, driver.getTitle());


        //This creates a list of the links we are going to validate
        ArrayList<String> linkTexts = new ArrayList<String>();
        linkTexts.add("About");
        linkTexts.add("Store");
        linkTexts.add("Gmail");
        linkTexts.add("Images");
        linkTexts.add("Privacy");
        linkTexts.add("Terms");


        //Here were are testing that each link opens

        for (String t : linkTexts) {
            driver.findElement(By.linkText(t)).click();
            if (driver.getTitle().equals("Google")) {

                System.out.println("\"" + t + "\""
                        + "Page does NOT open");
            } else {
                System.out.println("\"" + t + "\""
                        + "Page Succesfully Opens");
            }
            driver.navigate().back();

        }


        //This portion capture Image text from the Doodle
        // In case there is no text from the Doodle....we have a try and catch

        try {

            //This is the Image Doodle Text

            String imgTextDoodle = driver.findElement(By.xpath("//div[@id='hplogo']/a/img")).getAttribute("title");

            //Here we will place the "Image Text Doodle Into the Search Bar"

            driver.findElement(By.name("q")).sendKeys(imgTextDoodle);
            driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

            // This Captures the URL String and Replaces the Spaces
            String UrlString = driver.getCurrentUrl();
            String imgTextDoodleReplaced = imgTextDoodle.replace(" ", "+");


            //This validates that the URL string contains the "Image Doodle Text"

            if (!UrlString.contains(imgTextDoodleReplaced)) {
                System.out.println("The URL is Different");
            }

            //If the URL string does not contain the Image Doodle Text, The error message will be thrown
            else {
                System.out.println("The URL contains the Image Text Doodle ");
            }

            // This validates the search bar includes the img text being searched
            System.out.println("Current search: **" + driver.findElement(By.name("q")).getAttribute("value") + "**");
            System.out.println("Expected : **" + imgTextDoodle + "**");
            Assert.assertEquals(imgTextDoodle, driver.findElement(By.name("q")).getAttribute("value") + " ");


            // This clicks on the 3rd link available

            driver.findElement(By.xpath("(//div[@class='g'])[3]")).click();
            System.out.println(driver.getTitle());


            //This confirms that the page on the 3rd link was opened successfully
            JavascriptExecutor js = (JavascriptExecutor) driver;

            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                System.out.println("The page was loaded Succesfully");
            }


            //This confirms the page was redirected after the load

            String NewUrlString = driver.getCurrentUrl();

            if (NewUrlString.equals(UrlString)) {
                System.out.println("The webpage did not redirect");
            } else {
                System.out.println("The webpage did redirect");
            }

            driver.close();
            driver.quit();

        }

        //In case no doodle text exist...this message will be displayd
        catch (NoSuchElementException e) {
            System.out.println("There is no Doodle in Google today.");
            driver.close();
            driver.quit();
        }


    }
}
