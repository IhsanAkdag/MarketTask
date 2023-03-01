package Base;


import Utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class BaseTest {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    public static WebDriver driver;


    public synchronized static WebDriver getDriver() {
        return driver;
    }

    public synchronized static WebDriver setDriver(String browser) {

        if (driver == null) {


            browser = browser == null ? ConfigReader.getProperty("browser") : browser;



            switch (browser) {

                case "chrome":
                    ChromeOptions options = new ChromeOptions();

                    options.addArguments("--incognito"); //Chrome'u gizli modda açar
//                  options.addArguments("--headless"); //Chrome'u headless modda açar
                    options.addArguments("--start-maximized"); //Chrome'u büyütme modunda açar
                    options.addArguments("--ignore-certificate-errors"); // Chrome'da sertifika hatasını atlar
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--acceptInsecureCerts"); // Guvensiz sertifikalari kabul eder
                    options.addArguments("--disable-blink-features=AutomationControlled");
                    options.addArguments("--disable-extensions"); // Chrome tarayıcıdaki mevcut uzantıları devre dışı bırakır
                    options.addArguments("--disable-infobars"); // Chrome otomatik yazılım tarafından kontrol ediliyor" bildirimini görüntülemesini engeller

                    // Chrome tarayıcısında reklam engelleyici uzantısının nasıl etkinleştirileceğini gösterir ,boylelikle reklam engellenir
                    options.addExtensions(new File("/Users/APPLE/IdeaProjects/MerketTask/resources/extension_5_4_1_0.crx")); // crx dosyasinin yeri
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;

                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("-private-window");
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }

        }
        return driver;
    }


    @BeforeClass
    @Parameters(value = {"browser"})
    public synchronized void setupTest(@Optional String browser) throws MalformedURLException {

        driver = setDriver(browser);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

    }

//    @AfterClass
//    public synchronized void tearDown() throws Exception {
//        if (driver != null) {
//            driver.quit();
//        }
//        driver = null;
//    }


}