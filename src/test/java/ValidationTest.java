import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

public class ValidationTest {

    @Test
    void shouldNotPassValidationEmptyFields() {
        open("http://localhost:9999/");
        $(".form-field button").click();
        $("[data-test-id=name].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=phone].input_invalid").shouldNot(exist);
        $("[data-test-id=agreement].input_invalid").shouldNot(exist);
    }

    @Test
    void shouldNotPassValidationEmptyPhoneAndCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id=agreement].input_invalid").shouldNot(exist);
    }

    @Test
    void shouldNotPassValidationEmptyName() {
        open("http://localhost:9999/");
        $("[data-test-id=phone] input").setValue("+79820000000");
        $("[data-test-id=agreement] span.checkbox__box").click();
        $(".form-field button").click();
        $("[data-test-id=name].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotPassValidationEmptyPhone() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=agreement] span.checkbox__box").click();
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotPassValidationEmptyCheckbox() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79820000000");
        $(".form-field button").click();
        $("[data-test-id=agreement].input_invalid").should(exist);
    }

    @Test
    void shouldSendApplicationHyphenatedName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Петрова Эмилия-Анна");
        $("[data-test-id=phone] input").setValue("+79820000000");
        $("[data-test-id=agreement] span.checkbox__text").click();
        $(".form-field button").click();
        $("[data-test-id=order-success]").shouldHave(text(" Ваша заявка успешно отправлена!"));
    }

    @Test
    void shouldNotPassValidationLatinName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Bob");
        $(".form-field button").click();
        $("[data-test-id=name].input_invalid").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPassValidationSpecialCharName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван(:");
        $(".form-field button").click();
        $("[data-test-id=name].input_invalid").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPassValidationNumberName() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иван77");
        $(".form-field button").click();
        $("[data-test-id=name].input_invalid").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotPassValidationPhoneNumberOfDigitsLess11() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+7999112233");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhoneNumberOfDigitsMore11() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+799911223334");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhoneWithoutPlus() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("79991122333");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhonePlusAtTheEnd() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("79991122333+");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhoneLatin() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("phone79991122333");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhoneCyrillic() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("тел79991122333");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }

    @Test
    void shouldNotPassValidationPhoneSpecialChar() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("-79991122333");
        $(".form-field button").click();
        $("[data-test-id=phone].input_invalid").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр"));
    }
}
