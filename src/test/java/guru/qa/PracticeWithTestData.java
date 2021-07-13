package guru.qa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.TestData.firstName;
import static guru.qa.TestData.lastName;

public class PracticeWithTestData {
    @BeforeAll
    static void setup() {
        baseUrl = "https://demoqa.com";
        startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(firstName); // имя
        $("#lastName").setValue(lastName); // фамилия
        $("#userEmail").setValue("julia@qaguru.com"); // почта
        $("[name=gender][value=Female]").parent().click();
        $("#userNumber").setValue("0123456789"); // номер телефона
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption("March"); // месяц
        $(".react-datepicker__year-select").selectOption("1990"); // год
        $(".react-datepicker__day--004").click(); // день
        $("#subjectsInput").setValue("Biology").pressEnter(); // предметы
        $("#hobbiesWrapper").$(byText("Music")).click(); // хобби
        $("#uploadPicture").uploadFile(new File("src/test/resources/fox2.jpeg")); // картинка
        $("#currentAddress").setValue("Street1"); // адрес
        $("#react-select-3-input").setValue("NCR").pressEnter(); // штат
        $("#react-select-4-input").setValue("Noida").pressEnter(); // город

        $("#submit").click(); // клик по кнопке

        // сравнение введенных данных

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("tbody").$(byText("Student Name")).parent().shouldHave(text("Julia Fox"));
        $("tbody").$(byText("Student Email")).parent().shouldHave(text("julia@qaguru.com"));
        $("tbody").$(byText("Gender")).parent().shouldHave(text("Female"));
        $("tbody").$(byText("Mobile")).parent().shouldHave(text("0123456789"));
        $("tbody").$(byText("Date of Birth")).parent().shouldHave(text("04 March,1990"));
        $("tbody").$(byText("Subjects")).parent().shouldHave(text("Biology"));
        $("tbody").$(byText("Hobbies")).parent().shouldHave(text("Music"));
        $("tbody").$(byText("Picture")).parent().shouldHave(text("fox2.jpeg"));
        $("tbody").$(byText("Address")).parent().shouldHave(text("Street1"));
        $("tbody").$(byText("State and City")).parent().shouldHave(text("NCR Noida"));

    }


}
