package com.nttdata.steps;

import com.nttdata.page.ExamenComprasPages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ExamenComprasSteps {

    private WebDriver driver;
    //constructor
    public ExamenComprasSteps(WebDriver driver){
        this.driver = driver;
    }

    ExamenComprasPages examenComprasPages = new ExamenComprasPages();
    //-----------------------clothes----------------//

    public void seleccionarCategoria(String category) {
        seleccionarElemento(By.xpath("//*[@id='_desktop_top_menu']//a[contains(normalize-space(),'" + category + "')]"));
    }//*[@id="_desktop_top_menu"]

    public void seleccionarSubCategoria(String subCategory) {
        seleccionarElemento(By.xpath("//*[@id='left-column']/div[1]/ul/li[2]/ul//a[contains(text(),'" + subCategory + "')]"));
    }

    private void seleccionarElemento(By elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(elemento));
        categoryElement.click();
    }


    public String obtenerTitulo() {
        WebElement tituloElemento = this.driver.findElement(examenComprasPages.titulo);
        return tituloElemento.getText();
    }

    public int obetnerCantidad() {
        String cantidadTexto = this.driver.findElement(examenComprasPages.cantidad).getAttribute("value").trim();
        return Integer.parseInt(cantidadTexto);
    }

    public double obtenerPrecio() {
        WebElement precioElemento = this.driver.findElement(examenComprasPages.precio);
        return convertirPrecio(precioElemento.getText());
    }

    public double obtenerPrecioTotal() {
        WebElement precioTotalElemento = this.driver.findElement(examenComprasPages.precioTotal);
        return convertirPrecio(precioTotalElemento.getText());
    }

    private double convertirPrecio(String precioTexto) {
        //return Double.parseDouble(precioTexto.replace("", "").trim());
        return Double.parseDouble(precioTexto.replace("S/", "").trim());

    }


    public void selectProduct() {
        this.driver.findElement(examenComprasPages.producto).click();
    }

    public void cantidadProd(String quantity) {
        WebElement cantidadElemento = driver.findElement(examenComprasPages.cantidadProductors);
        cantidadElemento.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        cantidadElemento.sendKeys(quantity);
        // cantidadElemento.sendKeys("5");
        //

        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(ExpectedConditions.visibilityOfElementLocated(examenComprasPages.agregarAlCarrito));
    }


    public void agregarAlCarrito() {
        WebElement botonAgregar = driver.findElement(examenComprasPages.agregarAlCarrito);
        botonAgregar.click();
    }


    public void comprarBoton() {
        WebElement botonComprar = driver.findElement(examenComprasPages.comprar);
        botonComprar.click();

        new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public String getNombre() {
        return esperarYRecuperarTexto(examenComprasPages.nombre);
    }

    public String getPrecio() {
        return esperarYRecuperarTexto(examenComprasPages.precioCarro);
    }

    public String getTamano() {
        return esperarYRecuperarTexto(examenComprasPages.tamano);
    }

    public String getColor() {
        return esperarYRecuperarTexto(examenComprasPages.color);
    }

    public String getCantidadPro() {
        return esperarYRecuperarTexto(examenComprasPages.cantidadPro);
    }

    public String getPrecioTotal() {
        return esperarYRecuperarTexto(examenComprasPages.precioTotalCarrto);
    }

    private String esperarYRecuperarTexto(By elemento) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Reducción de la espera implícita
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(examenComprasPages.comprar));  // Espera explícita
        return this.driver.findElement(elemento).getText().trim();  // Recupera el texto y elimina espacios innecesarios
    }


}
