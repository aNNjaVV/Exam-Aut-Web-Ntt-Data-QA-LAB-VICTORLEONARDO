package com.nttdata.steps;

import com.nttdata.page.ProductosPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductosSteps {

    private WebDriver driver;
    //constructor
    public ProductosSteps(WebDriver driver){
        this.driver = driver;
    }

    public void selectProduct() {
        this.driver.findElement(ProductosPage.producto).click();
    }

    public void cantidadProd(String quantity) {
        WebElement cantidadElemento = driver.findElement(ProductosPage.cantidad);
        cantidadElemento.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        cantidadElemento.sendKeys(quantity);
       // cantidadElemento.sendKeys("5");
        //

        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfElementLocated(ProductosPage.agregarAlCarrito));
    }


    public void agregarAlCarrito() {
        WebElement botonAgregar = driver.findElement(ProductosPage.agregarAlCarrito);
        botonAgregar.click();
    }


    public void comprarBoton() {
        WebElement botonComprar = driver.findElement(ProductosPage.comprar);
        botonComprar.click();

        new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public String getNombre() {
        return esperarYRecuperarTexto(ProductosPage.nombre);
    }

    public String getPrecio() {
        return esperarYRecuperarTexto(ProductosPage.precio);
    }

    public String getTamano() {
        return esperarYRecuperarTexto(ProductosPage.tamano);
    }

    public String getColor() {
        return esperarYRecuperarTexto(ProductosPage.color);
    }

    public String getCantidadPro() {
        return esperarYRecuperarTexto(ProductosPage.cantidadPro);
    }

    public String getPrecioTotal() {
        return esperarYRecuperarTexto(ProductosPage.precioTotal);
    }

    private String esperarYRecuperarTexto(By elemento) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Reducción de la espera implícita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ProductosPage.comprar));  // Espera explícita
        return this.driver.findElement(elemento).getText().trim();  // Recupera el texto y elimina espacios innecesarios
    }


}
