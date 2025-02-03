package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps {

    private WebDriver driver;
    //constructor
    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    public void startPoint(){
        this.driver.findElement(LoginPage.startPoint).click();
    }

    public void userInput(String user){
        WebElement userFieldElement = driver.findElement(LoginPage.userInput);
        userFieldElement.sendKeys(user);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.loginButton));

    }

    public void passInput(String password){
        this.driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    public void loginButton(){
        this.driver.findElement(LoginPage.loginButton).click();
    }


    public boolean validateSession() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.userInfo));
            wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.logoutButton));
            return true;
        } catch (Exception e) {
            driver.close();
            return false;
        }
    }


}
