import Package.Authorization;
import Package.Questionnaire;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class QuestionnaireTest {
    WebDriver driver;
    Authorization objauthorization;
    Questionnaire objquestionnaire;
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();//подключение драйвера на хром
        driver.get("qa-test.html");// html тестового задания
        objauthorization = new Authorization(driver);
        objauthorization.login("test@protei.ru", "test");// авторизация на странице входа
        objquestionnaire = new Questionnaire(driver);
    }
    @Test
    public void QuestionnaireATest() {// Тестируем что анкете есть емеил
        objquestionnaire.setDataEmail("test@protei.ru");// вводим  email
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlEmail = objquestionnaire.getControlEmail();// вытаскиваем текст из ControlEmail
        assertThat(newGetControlEmail, notNullValue());// проверяем что текст не равен Null
    }
    @Test
    public void QuestionnaireBTest() {// Тестируем что анкете есть имя
        objquestionnaire.setDataEmail("test@protei.ru");// вводим  email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlName = objquestionnaire.getControlName();// вытаскиваем текст из ControlName
        assertThat(newGetControlName, notNullValue());// проверяем что текст не равен Null

    }
    @Test
    public void NegativeEmailTest(){// Тестируем даст ли ввести данные без емеил
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        String newGetEmailFormatError = objquestionnaire.getEmailFormatError();
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));
    }
    @Test
    public void NegativeNameTest() {// Тестируем даст ли ввести данные без имени
        objquestionnaire.setDataEmail("test@protei.ru");// вводим  email
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        String newGetBlankNameError = objquestionnaire.getBlankNameError();
        assertThat(newGetBlankNameError, is("Поле имя не может быть пустым"));// срасниваем текс ошибки
    }
    @Test
    public void NegativeANameTest() {// Тестируем даст ли ввести данные с некорректным "Заяц" (Дает)
        objquestionnaire.setDataEmail("test@protei.ru");// вводим  email
        objquestionnaire.setDataName("Заяц");//вводим имя
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlName = objquestionnaire.getControlName();// вытаскиваем текст из ControlName
        assertThat(newGetControlName,is("Заяц"));
    }
    @Test
    public void IncorrectEmailTest() {// Тестируем даст ли ввести данные с некорректным email
        objquestionnaire.setDataEmail("testprotei.ru");// вводим некорректный email "testprotei.ru"
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        String newGetEmailFormatError = objquestionnaire.getEmailFormatError();
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));// срасниваем текс ошибки
    }
    @Test
    public void FalseEmailATest() {// Тестируем даст ли ввести данные с некорректным email
        objquestionnaire.setDataEmail("test@protei.ру");// вводим некорректный email "test@protei.ру"
        objquestionnaire.setDataName("Миша");//вводим имя
        objquestionnaire.clickDataSend();//нажимаем кнопку добавить
        String newGetEmailFormatError = objquestionnaire.getEmailFormatError();// тест падает. дает содать анкету с  некорректным email
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));// срасниваем текс ошибки
    }
    @Test
    public void FalseEmailBTest() {// Тестируем даст ли ввести данные с некорректным email
        objquestionnaire.setDataEmail("testprotei.ru");// вводим некорректный email "test@protei.ру"
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickDataSend();// нажимаем кнопку добавить
        String newGetEmailFormatError = objquestionnaire.getEmailFormatError();// срасниваем текс ошибки
        assertThat(newGetEmailFormatError, is("Неверный формат E-Mail"));
    }
    @Test
    public void GenderMenTest() {// // Тестируем что анкете есть пол
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickMenСhooseGender();// выбираем мужской пол
        objquestionnaire.clickDataSend();// нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlGender = objquestionnaire.getControlGender();// вытаскиваем текст из ControlGender
        assertThat(newGetControlGender, notNullValue());// проверяем что текст не равен Null

    }
    @Test
    public void GenderWomanTest() {// Тестируем выбор женского пола
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickWomanСhooseGender();// выбираем женский пол
        objquestionnaire.clickDataSend();// нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlGender = objquestionnaire.getControlGender();// вытаскиваем текст из ControlGender
        assertThat(newGetControlGender, notNullValue());// проверяем что текст не равен Null
    }
    @Test
    public void GenderNegativeTest() {// Тестируем несовпадение пола и имени
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickMenСhooseGender();// выбираем мужской пол
        objquestionnaire.clickDataSend();// нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlGender = objquestionnaire.getControlGender(); // вытаскиваем текст "Данные добавлены."
        assertThat(newGetControlGender, is("Мужской")); //

    }
    @Test
    public void DataCheckTest(){ // тестируем чек бокс
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickWomanСhooseGender();// выбираем женский пол
        objquestionnaire.clickDataCheckA();// выбираем 1.1 чекбокс
        objquestionnaire.clickDataCheckB();// выбираем 1.2 чек бокс
        objquestionnaire.clickDataSend(); // нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlCheck = objquestionnaire.getControlCheck();// вытаскиваем текст из ControlCheck
        assertThat(newGetControlCheck,is("1.1, 1.2"));// проверяем что текст 1.1, 1.2
    }
    @Test
    public void DataSelectOneTest(){// тестируем чек бокс
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickWomanСhooseGender();// выбираем женский пол
        objquestionnaire.clickDataCheckA();// выбираем 1.1 чекбокс
        objquestionnaire.clickDataSelectA();// выбираем 2.1 чекбокс
        objquestionnaire.clickDataSend();// нажимаем кнопку добавить
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlSelect = objquestionnaire.getControlCheck();// вытаскиваем текст из ControlCheck
        assertThat(newGetControlSelect, notNullValue());// проверяем что текст не равен Null
    }
    @Test
    public void DataSelectTwoTest() {// тестируем чек бокс
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickMenСhooseGender();// выбираем мужской пол
        objquestionnaire.clickDataCheckA();// выбираем 1.1 чекбокс
        objquestionnaire.clickDataSelectB();// выбираем 2.2 чекбокс
        objquestionnaire.clickDataSend();// нажимаем кнопку ввод
        objquestionnaire.clickModalButton(); // нажимаем кнопку ок
        String newGetControlSelect = objquestionnaire.getControlSelect();// вытаскиваем текст из ControlSelect
        assertThat(newGetControlSelect, is("2.2"));// проверяем что текст равен 2.2
    }
    @Test
    public void DataSelectThreeTest() {// тестируем чек бокс
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Миша");// вводим имя
        objquestionnaire.clickMenСhooseGender();// выбираем мужской пол
        objquestionnaire.clickDataCheckA();// выбираем 1.1 чекбокс
        objquestionnaire.clickDataSelectC();// выбираем 2.3 чекбокс
        objquestionnaire.clickDataSend();// нажимаем кнопку ввод
        objquestionnaire.clickModalButton();// нажимаем кнопку ок
        String newGetControlSelect = objquestionnaire.getControlSelect();// вытаскиваем текст из ControlSelect
        assertThat(newGetControlSelect, notNullValue());// проверяем что текст не равен Null
    }
    @Test
    public void ModalContentTest() {// тестируем строку "Данные добавлены."
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Миша"); // вводим имя
        objquestionnaire.clickDataSend(); // нажимаем кнопку ввод
        String newGetModalContent = objquestionnaire.getModalContent(); // вытаскиваем текст "Данные добавлены."
        assertThat(newGetModalContent, is("Данные добавлены.")); // тест иногда падает иногда нет
    }
    @Test
    public void ModalContentNotNullTest(){ // тестируем что строка не равна Null
        objquestionnaire.setDataEmail("test@protei.ru");// вводим email
        objquestionnaire.setDataName("Маша");// вводим имя
        objquestionnaire.clickDataSend(); // нажимаем кнопку ввод
        String newGetModalContent = objquestionnaire.getModalContent();// вытаскиваем текст "Данные добавлены."
        assertThat(newGetModalContent, notNullValue());// проверяем что текст не равен Null
    }
    @After
    public void teardown() {// закрыть браузер
        driver.quit();
    }
}
