package com.example.store;
import com.example.pages.Checkout;
import com.example.pages.Home;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Case1 {
    private WebDriver driver;
    public Home homepage;
    public Checkout checkout;

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en-US");

        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demo.testim.io/");
    }

    @Test
    public void ValidateTitle(){
        homepage = new Home(this.driver);
        String title = homepage.getTitlePage();
        assertEquals(title,"Space & Beyond | Testim.io demo");
    }

    @Test
    public void searchDestiny(){
        homepage = new Home(this.driver);
        boolean isMadan = homepage.searchCardByName("Madan");
        assertTrue(isMadan);
    }

    @Test
    public void selectDateTravel(){
        homepage = new Home(this.driver);
        homepage.addDateTravel("Departing","18/01/2024");

    }

    @Test
    public void selectTickets(){
        homepage = new Home(this.driver);
        homepage.selectAdult("2");
        homepage.selectChildren("1");
    }

    @Test
    public void filterByColour(){
        homepage = new Home(this.driver);
        homepage.filterColourPlanet("Blue");
    }

    @Test
    public void bookedPlanet(){
        homepage = new Home(this.driver);
        homepage.filterColourPlanet("Blue");
        int cardMenu = homepage.countCard();
        homepage.selectCardBook("Tayabamba");
        assertEquals(cardMenu,3);
    }

    @Test
    public void filledCheckoutPhoto(){
        homepage = new Home(this.driver);
        checkout = new Checkout(this.driver);
        boolean isMadan = homepage.searchCardByName("Madan");
        assertTrue(isMadan);

        homepage.addDateTravel("Departing","18/01/2024");
        homepage.selectAdult("2");
        homepage.selectChildren("1");
        homepage.filterColourPlanet("Blue");
        int cardMenu = homepage.countCard();
        homepage.selectCardBook("Tayabamba");
        assertEquals(cardMenu,3);

        checkout.fillFormCheckout("Johanna Patricia","johanna@gmail.com","111-11-1111","2124567890");
        checkout.uploadPhoto("D:\\Descargas\\Ejemplo.png");
    }


    @Test
    public void payBookedWithPromotion(){
        homepage = new Home(this.driver);
        checkout = new Checkout(this.driver);
        boolean isMadan = homepage.searchCardByName("Madan");
        assertTrue(isMadan);

        homepage.addDateTravel("Departing","18/01/2024");

        homepage.selectAdult("2");
        homepage.selectChildren("1");
        homepage.filterColourPlanet("Blue");
        int cardMenu = homepage.countCard();
        homepage.selectCardBook("Tayabamba");
        assertEquals(cardMenu,3);

        checkout.fillFormCheckout("Johanna Patricia","johanna@gmail.com","111-11-1111","2124567890");
        checkout.uploadPhoto("D:\\Descargas\\Ejemplo.png");
        checkout.addPromotion("30076");
        checkout.selectPayNow();
    }

    @After
    public void close(){
        this.driver.close();
    }

}
