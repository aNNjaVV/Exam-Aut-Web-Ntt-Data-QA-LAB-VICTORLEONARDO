package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductosPage {
    //Localizadores de elementos
    public static By producto = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[1]/a/picture/img");
    public static By cantidad= By.xpath("//*[@id=\"quantity_wanted\"]");
    public static By agregarAlCarrito = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button");

    // Localizadores de elementos dentro del modal del carrito de compras
    // Estos elementos incluyen el nombre, tamaño, color, cantidad, precio individual, precio total y el botón de compra.
    public static By nombre = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/h6");
    public static By tamano = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[1]/strong");
    public static By color = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[2]/strong");
    public static By cantidadPro = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong");
    public static By precio = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/p");
    public static By precioTotal = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]");
    public static By comprar = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");

}
