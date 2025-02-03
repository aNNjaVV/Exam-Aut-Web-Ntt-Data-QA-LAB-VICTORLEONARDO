package com.nttdata.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ClothesMenSteps {

    private WebDriver driver;
    //constructor
    public ClothesMenSteps(WebDriver driver){
        this.driver = driver;
    }

    public void seleccionarCategoria(String category) {
        seleccionarElemento(By.xpath("//*[@id='_desktop_top_menu']//a[contains(normalize-space(),'" + category + "')]"));
    }

    public void seleccionarSubCategoria(String subCategory) {
        seleccionarElemento(By.xpath("//*[@id='left-column']/div[1]/ul/li[2]/ul//a[contains(text(),'" + subCategory + "')]"));
    }

    private void seleccionarElemento(By elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(elemento));
        categoryElement.click();
    }

    public boolean categoriaEstaDisponible(String category) {
        return estaDisponible(By.xpath("//*[@id='_desktop_top_menu']//a[contains(normalize-space(),'" + category + "')]"));
    }

    public boolean subcategoriaEstaDisponible(String subCategory) {
        return estaDisponible(By.xpath("//*[@id='left-column']/div[1]/ul/li[2]/ul//a[contains(text(),'" + subCategory + "')]"));
    }

    private boolean estaDisponible(By elemento) {
        try {
            driver.findElement(elemento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
