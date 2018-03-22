package com.globant.automation;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PedidosYaTest {
    
	private static final Logger LOG;
    private WebDriver driver;
    
    static {
        LOG = LogManager.getLogger(GoogleTest.class);
    }
    
    @Test
    public void testPedidosYa() {
    	driver.get("http://www.pedidosya.com");
    	PedidosYaCountrySel CountrySel=new PedidosYaCountrySel(driver);
    	PedidosYaUruguayMain UruguayMain = CountrySel.clickOnUruguay();
    	UruguayMain.ingresarDireccion("Nicaragua 1666");
    	UruguayMain.ingresarComidaRestaurante("Milanesa");
    	ConfirmPopUp ConfirmPage=UruguayMain.clickEnBuscar();
    	PedidosYaAgregarAlPedido PaginaResultadosSugerencias=ConfirmPage.confirmarUbicacion();
    	PedidosYaAgregarPopUp AgregarPopUp = PaginaResultadosSugerencias.clickearSugerencia("Milanesa completa al pan con papas");
    	String PrecioAntesAgregar = AgregarPopUp.getPrice();
    	String NombreAntesAgregar = AgregarPopUp.getPlateName();
    	LOG.info(PrecioAntesAgregar);
    	LOG.info(NombreAntesAgregar);
    	CheckingOutPage VerifPedido = AgregarPopUp.clickAgregarAlPedido();
    	String DirPosAgregado = VerifPedido.getDireccion();
    	LOG.info(DirPosAgregado);
    	String PrecioPosAgregado = VerifPedido.getPrecio();
    	LOG.info(PrecioPosAgregado);
    	assertEquals(DirPosAgregado,"Delivery para Nicaragua 1666","Verifico la Direcci�n de envio");
    	assertEquals(PrecioAntesAgregar,PrecioPosAgregado,"Verifico el precio");
    	LogInPopUp PaginaLogIn=VerifPedido.clickContinuar();
    	PaginaLogIn.ingresarUsuario("cristian.j.vazquez@gmail.com");
    	PaginaLogIn.ingresarPassword("pruebapedidosya");
    	deliveryCheckoutPage PaginaEntrega = PaginaLogIn.clickLoginButton();
    	String NombreDelUsuarioLoggueado= PaginaEntrega.getUserLoggedName();
    	assertEquals(NombreDelUsuarioLoggueado,"Cristian","Verifico que se haya loggueado bien");
    }
	
   	@BeforeMethod
   	public void prepareTest() {
   	    LOG.info("Prepare test");
   	    driver = new ChromeDriver();
   	}
   	@BeforeClass
   	private void prepareClass() {
   	    LOG.info("Prepare class");
   	    WebDriverManager.chromedriver().setup();
   	}
}