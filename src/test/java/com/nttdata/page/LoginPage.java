package com.nttdata.page;

import org.openqa.selenium.By;

public class LoginPage {
    //Localizadores de elementos
    public static By startPoint = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a/span");
    public static By userInput = By.xpath("//*[@id=\"field-email\"]");
    public static By passInput = By.xpath("//*[@id=\"field-password\"]");
    public static By loginButton = By.xpath("//*[@id=\"submit-login\"]");
    public static By userInfo = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span");
    public static By logoutButton = By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[1]");
}
