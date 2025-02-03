package com.nttdata.steps;

import com.nttdata.page.CarritoComprasPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CarritoComprasSteps {

    private WebDriver driver;
    //constructor
    public CarritoComprasSteps(WebDriver driver){
        this.driver = driver;
    }

    public String obtenerTitulo() {
        WebElement tituloElemento = this.driver.findElement(CarritoComprasPage.titulo);
        return tituloElemento.getText();
    }

    public int obetnerCantidad() {
        String cantidadTexto = this.driver.findElement(CarritoComprasPage.cantidad).getAttribute("value").trim();
        return Integer.parseInt(cantidadTexto);
    }

    public double obtenerPrecio() {
        WebElement precioElemento = this.driver.findElement(CarritoComprasPage.precio);
        return convertirPrecio(precioElemento.getText());
    }

    public double obtenerPrecioTotal() {
        WebElement precioTotalElemento = this.driver.findElement(CarritoComprasPage.precioTotal);
        return convertirPrecio(precioTotalElemento.getText());
    }

    private double convertirPrecio(String precioTexto) {
        //return Double.parseDouble(precioTexto.replace("", "").trim());
        return Double.parseDouble(precioTexto.replace("S/", "").trim());

    }

}
