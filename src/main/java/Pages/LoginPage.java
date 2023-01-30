package Pages;

import Base.BaseStepMethod;
import Utilities.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


public class LoginPage extends BaseStepMethod {
    private static final Logger LOGGER= LogManager.getLogger(LoginPage.class);

    private static final By INPUT_LOGINPAGE_EMAIL = By.id("txtUserName");
    private static final By BTN_LOGINPAGE_LOGINBUTON1 = By.id("btnLogin");
    private static final By INPUT_LOGINPAGE_PASSWORD=By.xpath("//input[@placeholder='Şifre']");
    private static final By BTN_LOGINPAGE_LOGINBUTTON2 = By.id("btnEmailSelect");


    public void loginHepsiBurada(String email,String password){
        setTextElement(INPUT_LOGINPAGE_EMAIL, email);
        LOGGER.info("input email loginpage");

        clickElement(BTN_LOGINPAGE_LOGINBUTON1);
        LOGGER.info("buton loginpage");

        setTextElement(INPUT_LOGINPAGE_PASSWORD,password);
        LOGGER.info("input password loginpage");

        clickElement(BTN_LOGINPAGE_LOGINBUTTON2);
        LOGGER.info("btn2 loginpage");

    }

}