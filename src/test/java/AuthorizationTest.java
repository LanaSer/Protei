import Package.Authorization;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
public class AuthorizationTest {
    WebDriver driver;
    Authorization objauthorization;
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();//подключение драйвера на хром
        driver = new ChromeDriver();
        driver.get("qa-test.html"); // html тестового задания
        objauthorization = new Authorization(driver);
    }
     @Test
    public void positiveAuthorizationTest() { // обычная авторизация
        objauthorization.login("test@protei.ru","test");
     }
    @Test
    public void noPasswordTest() {// авторизация без пароля
        objauthorization.setLoginEmail("test@protei.ru");// вводим email
        objauthorization.clickAutoButton();//нажатия на кнопку Вход
        String newGetEmailPassword = objauthorization.getEmailPassword();
        assertThat(newGetEmailPassword, is("Неверный E-Mail или пароль")); // проверяем текст ошибки
    }
    @Test
    public void noEmailTest() {// авторизация без email
        objauthorization.setLoginPassword("test");// вводим пароль
        objauthorization.clickAutoButton();//нажатия на кнопку Вход
        String newGetEmailFormatError = objauthorization.getEmailFormatError();
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));// проверяем текст ошибки
    }
     @Test
     public void negativePasswordTest(){// авторизация с неверным паролем
         objauthorization.login("test@protei.ru","tyty");
         String newGetEmailPassword = objauthorization.getEmailPassword();
         assertThat(newGetEmailPassword, is("Неверный E-Mail или пароль")); // проверяем текст ошибки
     }
    @Test
    public void negativeEmailTest(){// авторизация с некорректным email
        objauthorization.login("test@protei.ру","test");
        String newGetEmailPassword = objauthorization.getEmailPassword();
        assertThat(newGetEmailPassword, is("Неверный E-Mail или пароль"));// проверяем текст ошибки
}   @Test
    public void negativeEmailATest(){// авторизация с некорректным написаным email
        objauthorization.login("test@protei.com","test");
        String newGetEmailPassword = objauthorization.getEmailPassword();
        assertThat(newGetEmailPassword, is("Неверный E-Mail или пароль"));// проверяем текст ошибки
    }
    @Test
    public void negativeEmailBTest(){// авторизация с некорректным написаным email
        objauthorization.login("testprotei.ru","test");
        String newGetEmailFormatError = objauthorization.getEmailFormatError();
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));// проверяем текст ошибки
    }
    @After
    public void teardown() {// закрыть браузер
        driver.quit();
    }
}



