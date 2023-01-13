package Package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class Authorization {

    private final By loginEmail = By.id("loginEmail");// локатор для ввода Email
    private final By loginPassword = By.id("loginPassword");// локатор для ввода пароля
    private final By authButton = By.id("authButton");// локатор для кнопки "Вход"
    private final By  invalidEmailPassword = By.id("invalidEmailPassword");// локатор для ошибки "Неверный E-Mail или пароль"
    private final By  emailFormatError = By.id("emailFormatError");// локатор для ошибки "Неверный формат E-Mail"

    private final WebDriver driver;
    public Authorization(WebDriver driver) {
        this.driver = driver;
    }
    public void setLoginEmail(String username){// метод для запонения поля "Email"
        driver.findElement(loginEmail).sendKeys(username);
    }
    public void setLoginPassword(String password){ // метод для запонения поля "Пароль"

        driver.findElement(loginPassword).sendKeys(password);
    }
    public void clickAutoButton(){ // метод для нажатия на кнопку "Вход"
        driver.findElement(authButton).click();
    }
    public void login(String username, String password){ // метод для Авторизации
        setLoginEmail(username);
        setLoginPassword(password);
        clickAutoButton();
    }
    public String getEmailPassword(){ //метод для вытаскивания текста ошибки "Неверный E-Mail или пароль"
        return driver.findElement(invalidEmailPassword).getText();
    }
    public String getEmailFormatError(){ //метод для вытаскивания текста ошибки "Неверный формат E-Mail"
        return driver.findElement(emailFormatError).getText();
    }
}
