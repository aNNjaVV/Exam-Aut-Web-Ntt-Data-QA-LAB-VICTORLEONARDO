package com.nttdata.page;

import org.openqa.selenium.By;

public class CarritoComprasPage {
    //Localizadores de elementos
    public static By titulo = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/h1");
    public static By cantidad = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input");
    public static By precio = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");
    public static By precioTotal = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[2]/span/strong");
}
