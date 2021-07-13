package guru.qa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTestsWithPageobjects {

    RegistrationPage registrationPage = new RegistrationPage();



    @BeforeAll
    static void setup() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        String firstName = "Julia";

        registrationPage.openPage();
        registrationPage.typeFirstName("Julia"); // имя
        registrationPage.typeLastName("Fox"); // фамилия
        registrationPage.typeEmail("julia@qaguru.com")
                .selectGender("Female")
                .typePhone("0123456789")
                .setDateOfBirth("4","March","1990");
        $("#subjectsInput").setValue("Biology").pressEnter(); // предметы
        $("#hobbiesWrapper").$(byText("Music")).click(); // хобби
        $("#uploadPicture").uploadFile(new File("src/test/resources/fox2.jpeg")); // картинка
        $("#currentAddress").setValue("Street1"); // адрес
        $("#react-select-3-input").setValue("NCR").pressEnter(); // штат
        $("#react-select-4-input").setValue("Noida").pressEnter(); // город

        $("#submit").click(); // клик по кнопке

        sleep(5000);

        // сравнение введенных данных

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(firstName + " Fox");
        $("tbody").$(byText("Student Email")).parent().shouldHave(text("julia@qaguru.com"));
        $("tbody").$(byText("Gender")).parent().shouldHave(text("Female"));
        $("tbody").$(byText("Mobile")).parent().shouldHave(text("0123456789"));
        $("tbody").$(byText("Date of Birth")).parent().shouldHave(text("4 March,1990"));
        $("tbody").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $("tbody").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $("tbody").$(byText("Picture")).parent().shouldHave(text("fox2.jpeg"));
        $("tbody").$(byText("Address")).parent().shouldHave(text("Street1"));
        $("tbody").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));

    }


}
