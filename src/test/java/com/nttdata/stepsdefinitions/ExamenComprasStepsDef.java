package com.nttdata.stepsdefinitions;

import com.nttdata.steps.ExamenComprasSteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class ExamenComprasStepsDef {

    private WebDriver driver;

    private ExamenComprasSteps examenComprasSteps(WebDriver driver) {
        return new ExamenComprasSteps(driver);
    };

    private LoginSteps loginSteps(WebDriver driver) {
        return new LoginSteps(driver);
    };

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPáginaDeLaTienda() {
        final String mensajeError = "Error: No se pudo acceder a la página de la tienda. :(";
        try {
            driver = getDriver();
            driver.get("https://qalab.bensg.com/store/pe/");
            screenShot();
        } catch (Exception e) {
            Assertions.fail(mensajeError); // Mensaje de errorr
            screenShot();
            driver.close();
        }
    }


    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String usuario, String password) {

        final String mensajeError = "Error: Las credenciales proporcionadas son incorrectas. El proceso ha sido detenido. :(";
        final String mensajeExito = "El ingreso fue exitoso. Sesión iniciada correctamente. :)";

        loginSteps(driver).startPoint();
        screenShot();
        loginSteps(driver).userInput(usuario);
        loginSteps(driver).passInput(password);
        screenShot();
        loginSteps(driver).loginButton();

        if (!loginSteps(driver).validateSession()) {
            Assertions.fail(mensajeError);
            screenShot();
            driver.close();
        } else {
            System.out.println(mensajeExito); // Mensaje de éxito
        }
        screenShot();
    }


    @When("navego a la categoría {string} y subcategoría {string}")
    public void navegoALaCategoriaYSubcategoria(String category, String subCategory) {
        try {
            examenComprasSteps(driver).seleccionarCategoria(category);
            screenShot();

            examenComprasSteps(driver).seleccionarSubCategoria(subCategory);
            screenShot();
        } catch (Exception e) {
            Assertions.fail("Error al navegar a la categoría y subcategoría. Detalles: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }

    @And("agrego 2 unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito() {
        final String mensajeError = "Error: No se pudo agregar el producto al carrito. :(";
        final String mensajeExito = "El producto fue agregado correctamente al carrito. ;=)";

        try {
            examenComprasSteps(driver).selectProduct();
            examenComprasSteps(driver).cantidadProd("10");

            screenShot();

            System.out.println(mensajeExito);
        } catch (Exception e) {
            Assertions.fail(mensajeError + " Detalles del error: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }


    @Then("valido en el popup la confirmación del producto agregado")
    public void validoPopupConfirmacionProductoAgregado() {

        final String mensajeError = "Error: Los datos del producto en el popup no coinciden con los esperados. :/";
        final String mensajeExito = "El producto fue confirmado correctamente en el popup. :)";

        try {
            examenComprasSteps(driver).agregarAlCarrito();

            var producto = examenComprasSteps(driver);

            Assertions.assertAll(
                    () -> Assertions.assertEquals("Hummingbird printed t-shirt", producto.getNombre()),
                    () -> Assertions.assertEquals("S/ 19.12", producto.getPrecio()),
                    () -> Assertions.assertEquals("S", producto.getTamano()),
                    () -> Assertions.assertEquals("Blanco", producto.getColor()),
                    () -> Assertions.assertEquals("10", producto.getCantidadPro())
            );

            System.out.println(mensajeExito);
            screenShot();
        } catch (AssertionError e) {
            Assertions.fail(mensajeError + " Detalles del error: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }


    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        final String mensajeError = "Error: El monto total calculado en el popup no coincide con el esperado.";
        final String mensajeExito = "El monto total fue calculado correctamente en el popup.";

        try {
            double precioProducto = Double.parseDouble(examenComprasSteps(driver).getPrecio().replace("S/ ", "").trim());
            int cantidad = Integer.parseInt(examenComprasSteps(driver).getCantidadPro());
            double total = Double.parseDouble(examenComprasSteps(driver).getPrecioTotal().replace("S/ ", "").trim());
            double totalEsp = precioProducto * cantidad;


            double delta = 0.0001;
            Assertions.assertEquals(totalEsp, total, delta, mensajeError);

            System.out.println(mensajeExito);
            screenShot();
        } catch (AssertionError e) {
            Assertions.fail(mensajeError + " Detalles del error: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }




    @When("finalizo la compra")
    public void finalizoLaCompra() {
        final String mensajeError = "Error: No se pudo completar la compra. El proceso ha fallado.";
        final String mensajeExito = "Compra completada exitosamente.";
        try {
            examenComprasSteps(driver).comprarBoton();
            System.out.println(mensajeExito);
            screenShot();
        } catch (Exception e) {
            Assertions.fail(mensajeError + " Detalles del error: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }



    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        final String mensajeError = "Error: El título de la página del carrito no coincide.";
        final String mensajeExito = "El título de la página del carrito es correcto.";
        try {
            String titulo = examenComprasSteps(driver).obtenerTitulo();
            Assertions.assertEquals("CARRITO", titulo);
            //Assertions.assertEquals("TIENDITA", titulo);
            System.out.println(mensajeExito);
            screenShot();
        } catch (AssertionError e) {
            Assertions.fail(mensajeError);
            screenShot();
            driver.close();
        }
    }



    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        final String mensajeError = "Error: El cálculo del precio total en el carrito es incorrecto.";
        final String mensajeExito = "El cálculo del precio total en el carrito es correcto.";

        try {
            double precio = examenComprasSteps(driver).obtenerPrecio();
            int cant = examenComprasSteps(driver).obetnerCantidad();
            double total = examenComprasSteps(driver).obtenerPrecioTotal();

            double totalEsp = precio * cant;
            double delta = 0.0001;
            Assertions.assertEquals(totalEsp, total, delta, mensajeError);

            System.out.println(mensajeExito);
            screenShot();
        } catch (AssertionError e) {

            Assertions.fail(mensajeError + " Detalles del error: " + e.getMessage());
            screenShot();
            driver.close();
        }
    }




}
