package Package;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Questionnaire {
    private final By dataEmail = By.id("dataEmail");// локатор для ввода Email
    private final By dataName = By.id("dataName");// локатор для ввода имя
    private final By dataGender = By.id("dataGender");// локатор для выбора пола

    private final By menChooseGender = By.xpath("//*[@id=\"dataGender\"]/option[1]"); // локатор для выбора мужского пола
    private final By womanChooseGender = By.xpath("//*[@id=\"dataGender\"]/option[2]"); // локатор для выбора женского пола

    private final By dataCheckA = By.id("dataCheck11"); // локатор для чекбокса "Вариант 1.1"
    private final By dataCheckB = By.id("dataCheck12"); // локатор для чекбокса "Вариант 1.2"
    private final By dataSelectA = By.id("dataSelect21"); // локатор для чекбокса "Вариант 2.1"
    private final By dataSelectB = By.id("dataSelect22"); // локатор для чекбокса "Вариант 2.2"
    private final By dataSelectC = By.id("dataSelect23"); // локатор для чекбокса "Вариант 2.3"
    private final By dataSend = By.id("dataSend"); // локатор для кнопки "Добавить"

    private final By emailFormatError = By.id("emailFormatError"); // локатор для ошибки "Неверный формат E-Mail"
    private final By blankNameError = By.id("blankNameError"); // локатор для ошибки "Поле имя не может быть пустым"
    public final By modalContent = By.xpath("/html/body/div[3]/div/div/div[1]"); // локатор надписи "Данные добавлены."
    public final By modalButton = By.xpath("/html/body/div[3]/div/div/div[2]/button"); // локатор для кнопки "Ок"

    public final By controlEmail = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[1]");
    public final By controlName = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[2]");
    public final By controlGender = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[3]");
    public final By controlCheck = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[4]");
    public final By controlSelect = By.xpath("//*[@id=\"dataTable\"]/tbody/tr/td[5]");

    private final WebDriver driver;
    public Questionnaire(WebDriver driver) {
        this.driver = driver;
    }
    public void setDataEmail(String email){  // метод для запонения поля "Email"

        driver.findElement(dataEmail).sendKeys(email);
    }
    public void setDataName(String username){ // метод для запонения поля "Имя"

        driver.findElement(dataName).sendKeys(username);
    }
    public void clickMenСhooseGender(){ // метод для выбора Мужского пола
        driver.findElement(dataGender).click();
       driver.findElement(menChooseGender).click();
    }
    public void clickWomanСhooseGender(){ // метод для выбора Женского пола
        driver.findElement(dataGender).click();
        driver.findElement(womanChooseGender).click();
    }
    public void clickDataCheckA(){ // метод для чекбокса "Вариант 1.1"
        driver.findElement(dataCheckA).click();
    }
    public void clickDataCheckB(){ // метод для выбора чекбокса "Вариант 1.2"
        driver.findElement(dataCheckB).click();
    }
    public void clickDataSelectA(){// метод для выбора чекбокса "Вариант 2.1"
        driver.findElement(dataSelectA).click();
    }
    public void clickDataSelectB(){// метод для выбора чекбокса "Вариант 2.2"
        driver.findElement(dataSelectB).click();
    }
    public void clickDataSelectC(){// метод для выбора чекбокса "Вариант 2.3"
        driver.findElement(dataSelectC).click();
    }
    public void clickDataSend(){// метод для кнопки "Добавить"
        driver.findElement(dataSend).click();
    }
    public void clickModalButton(){ // метод для кнопки "Ок"
        driver.findElement(modalButton).click();
    }
    public String getEmailFormatError(){ //метод для вытаскивания текста ошибки "Неверный формат E-Mail"
        return driver.findElement(emailFormatError).getText();
    }
    public String getBlankNameError(){ //метод для вытаскивания текста ошибки "Поле имя не может быть пустым"
        return driver.findElement(blankNameError).getText();
    }
    public String getModalContent(){ //метод для вытаскивания текста "Данные добавлены."
        return driver.findElement(modalContent).getText();
    }
    public String getControlEmail(){ //метод для вытаскивания текста из controlEmail
        return driver.findElement(controlEmail).getText();
    }
    public String getControlName(){ //метод для вытаскивания текста controlName
        return driver.findElement(controlName).getText();
    }public String getControlGender(){ //метод для вытаскивания текста controlGender
        return driver.findElement(controlGender).getText();
    }
    public String getControlCheck(){ //метод для вытаскивания текста controlCheck
        return driver.findElement(controlCheck).getText();
    }
    public String getControlSelect() { //метод для вытаскивания текста из controlSelect
        return driver.findElement(controlSelect).getText();


    }
}
