package pages;

import com.codeborne.selenide.SelenideElement;
import components.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class RegistrationPage {
    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private SelenideElement modal = $("[role=dialog]");

    private Calendar calendar = new Calendar();

    public void openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }

    public void typeFirstName(String firstName){
        $("#firstName").setValue(firstName);
    }

    public void typeLastName(String lastName){
        $("#lastName").setValue(lastName);
    }

    public RegistrationPage typeEmail(String email){
        $("#userEmail").setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender){
        //$("[name=gender][value=Female]").parent().click();
        $(format("[name=gender][value=%s]", gender)).parent().click();

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year){
        calendar.setDate(day,month,year);
        return this;
    }

    public RegistrationPage typePhone(String phone){
        $("#userNumber").setValue(phone);
        return this;
    }

    public void checkResultsTitle(){
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
    }

    public void checkResultsValue(String value){
        modal.$(".table-responsive").shouldHave(text(value));

    }

}
