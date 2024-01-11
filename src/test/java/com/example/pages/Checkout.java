package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Checkout {
    private WebDriver driver;

    public Checkout(WebDriver driver){
        this.driver = driver;
    }

    public void fillFormCheckout(String name,String email,String socialNumber,String phone){
        this.driver.findElement(By.xpath("//span[.='Name']//preceding-sibling::input[1]")).sendKeys(name);
        this.driver.findElement(By.xpath("//span[.='Email Address']//preceding-sibling::input[1]")).sendKeys(email);
        this.driver.findElement(By.xpath("//span[.='Social Security Number']//preceding-sibling::input[1]")).sendKeys(socialNumber);
        this.driver.findElement(By.xpath("//span[.='Phone Number']//preceding-sibling::input[1]")).sendKeys(phone);
    }

    public void uploadPhoto(String pathPhoto){
        this.driver.findElement(By.cssSelector("input[type='file']")).sendKeys(pathPhoto);
    }

    public void addPromotion(String codPromotion){
        this.driver.findElement(By.name("promo")).sendKeys(codPromotion);
        this.driver.findElement(By.xpath("//button[.='Apply']")).click();
    }

    public void selectPayNow(){
        this.driver.findElement(By.cssSelector("[data-react-toolbox='check']")).click();
        this.driver.findElement(By.xpath("//button[.='Pay now']")).click();
    }
}
