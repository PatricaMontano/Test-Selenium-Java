package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static java.util.Map.entry;

public class Home {
    private WebDriver driver;

    public Home(WebDriver driver){
        this.driver = driver;
    }

    public String getTitlePage(){
        return this.driver.getTitle();
    }

    public boolean searchCardByName(String name){
        WebElement card = this.driver.findElement(By.xpath("//h5[.='"+name+"']"));
        return Objects.equals(card.getText(), name);
    }

    public void addDateTravel(String tipoFecha,String fecha){
        this.driver.findElement(By.xpath("//label[.='"+tipoFecha+"']//preceding-sibling::input[1]")).click();
        String [] fechaFormated = fecha.split("/");

        int day = Integer.parseInt(fechaFormated[0]);
        int month = Integer.parseInt(fechaFormated[1]);
        String year = fechaFormated[2];

        this.driver.findElement(By.id("years")).click();
        this.driver.findElement(By.id(year)).click();

        Map<String, Integer> dictionaryMonths = Map.ofEntries(
                entry("January", 1),
                entry("February", 2),
                entry("March", 3),
                entry("April", 4),
                entry("May", 5),
                entry("June", 6),
                entry("July", 7),
                entry("August", 8),
                entry("September", 9),
                entry("October", 10),
                entry("November", 11),
                entry("December", 12)
        );

        String datePickerMonth = this.driver.findElement(By.cssSelector("[data-react-toolbox='month'] .theme__title___2Ue3-")).getText();
        String pickerMonth = datePickerMonth.substring(0, datePickerMonth.indexOf(" "));

        int numberMonthPicker = dictionaryMonths.get(pickerMonth);
        int result = month - numberMonthPicker;

        while (result != 0) {
            if(result>0){
                this.driver.findElement(By.id("right")).click();
                this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                result--;
            }
            if(result<0){
                this.driver.findElement(By.id("left")).click();
                this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                result++;
            }
        }
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        List<WebElement> resultDays = this.driver.findElements(By.cssSelector("div[data-react-toolbox='day']"));
        resultDays.get(day-1).click();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        this.driver.findElement(By.xpath("//button[.='Ok']")).click();
    }

    public void selectAdult(String count){
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        this.driver.findElement(By.cssSelector("[value='Adults (18+)']")).click();
        this.driver.findElement(By.xpath("//li[.='Adults (18+)']//following-sibling::li['"+count+"']")).click();
    }

    public void selectChildren(String count){
        this.driver.findElement(By.cssSelector("[value='Children (0-7)']")).click();
        this.driver.findElement(By.xpath("//li[.='Children (0-7)']//following-sibling::li['"+count+"']")).click();
    }

    public  void filterColourPlanet(String color){
        this.driver.findElement(By.cssSelector("[value='Planet color']")).click();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        this.driver.findElement(By.xpath("//li[.='"+color+"']")).click();
    }

    public int countCard(){
        List<WebElement> cardsMenu = this.driver.findElements(By.cssSelector("[data-react-toolbox='card']"));
        return cardsMenu.size();
    }

    public void selectCardBook(String name){
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebElement titleCard = this.driver.findElement(By.xpath("//h5[.='"+name+"']//ancestor::div[@data-react-toolbox='card']"));
        titleCard.findElement(By.cssSelector("button[data-react-toolbox='button']")).click();
    }
}
