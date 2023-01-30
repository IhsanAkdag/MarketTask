import Base.BaseTest;
import Pages.AllPages;
import Utilities.ConfigReader;
import org.testng.annotations.Test;
public class TC001 extends BaseTest {
    AllPages allPages=new AllPages();
    @Test
    public void test01(){
        allPages.mainPage().go_To_Url(ConfigReader.getProperty("baseURL"));
        allPages.mainPage().hoverOverGirisYapButon();
        allPages.loginPage().loginHepsiBurada(ConfigReader.getProperty("UserMail"),ConfigReader.getProperty("UserPassword"));
        allPages.userDashboardPage().verifyValidLogin("Note Uc");
        allPages.userDashboardPage().searchProduct("dizustu bilgisayar");

    }
}