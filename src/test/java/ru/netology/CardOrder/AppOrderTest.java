package ru.netology.CardOrder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.*;

public class AppOrderTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequestWithValidData() {
        $("[type=text]").setValue("Филипп Иванов");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в " +
                        "ближайшее время."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate1() {
        $("[type=text]").setValue("Gagarin Yuri");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub")
                .shouldHave(exactText("  Имя и Фамилия указаные неверно. Допустимы только русские буквы," +
                        " пробелы и дефисы."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate2() {
        $("[type=text]").setValue("123");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub")
                .shouldHave(exactText("  Имя и Фамилия указаные неверно. Допустимы только русские буквы, " +
                        "пробелы и дефисы."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate3() {
        $("[type=text]").setValue("#$Алеша");
        $("[data-test-id=phone] input").setValue("+79123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub")
                .shouldHave(exactText("  Имя и Фамилия указаные неверно. Допустимы только русские буквы," +
                        " пробелы и дефисы."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate4() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("79123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub")
                .shouldHave(exactText("  Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate5() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("++7912345678");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub")
                .shouldHave(exactText("  Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate6() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("879123456789");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub")
                .shouldHave(exactText("  Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldReturnWarningThatValueNotValidate7() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("791234");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub")
                .shouldHave(exactText("  Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldReturnWarningThatFieldShouldBeFilled1() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub")
                .shouldHave(exactText("  Поле обязательно для заполнения"));
    }

    @Test
    void shouldReturnWarningThatFieldShouldBeFilled2() {
        $("[type=text]").setValue("");
        $("[data-test-id=phone] input").setValue("+79000000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub")
                .shouldHave(exactText("  Поле обязательно для заполнения"));
    }

    @Test
    void shouldReturnWarningCheckboxShouldBeClicked() {
        $("[type=text]").setValue("Сергей Бурдяков");
        $("[data-test-id=phone] input").setValue("+79000000101");
        $("[type=button]").click();
        $(".input_invalid")
                .shouldHave(exactText("  Я соглашаюсь с условиями обработки и использования моих персональных " +
                        "данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
