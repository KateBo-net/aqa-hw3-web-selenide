import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;

public class HappyPathTest {

    @Test
    void shouldSendApplication() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79820000000");
        $("[data-test-id=agreement] span.checkbox__box").click();
        $(".form-field button").click();
        $("[data-test-id=order-success]").shouldHave(text(" Ваша заявка успешно отправлена!"));
    }
}
