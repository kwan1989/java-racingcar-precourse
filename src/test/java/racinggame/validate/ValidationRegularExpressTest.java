package racinggame.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import racinggame.exception.CustomException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@TestMethodOrder(MethodOrderer.MethodName.class)
class ValidationRegularExpressTest {
    ValidationRegularExpress validationRegularExpress;

    @BeforeEach
    void init() {
        validationRegularExpress = new ValidationRegularExpress();
    }

    @Test
    void TC1_소문자콤마조합() throws CustomException {
        String testCase1 = "pobi,kwan,soree";
        String testCase2 = "Pobi,kwan,soree";
        String testCase3 = "포비,kwan,soree";
        String testCase4 = "pobi3,kwan,soree";

        assertThat(validationRegularExpress.isValidationNameRegularExpress(testCase1)).isTrue();

        assertThatThrownBy(() -> validationRegularExpress.isValidationNameRegularExpress((testCase2))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 입력이 잘못 되었습니다.");

        assertThatThrownBy(() -> validationRegularExpress.isValidationNameRegularExpress((testCase3))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 입력이 잘못 되었습니다.");

        assertThatThrownBy(() -> validationRegularExpress.isValidationNameRegularExpress((testCase4))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 입력이 잘못 되었습니다.");
    }

    @Test
    void TC2_숫자만기입하기() throws CustomException {
        String testCase1 = "1";
        String testCase2 = "2";
        String testCase3 = "s";
        String testCase4 = "한";

        assertThat(validationRegularExpress.isValidationNumberRegularExpress(testCase1)).isTrue();

        assertThat(validationRegularExpress.isValidationNumberRegularExpress(testCase2)).isTrue();

        assertThatThrownBy(() -> validationRegularExpress.isValidationNumberRegularExpress((testCase3))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 숫자만 기입하여 주세요.");

        assertThatThrownBy(() -> validationRegularExpress.isValidationNumberRegularExpress((testCase4))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 숫자만 기입하여 주세요.");
    }

    @Test
    void TC3_길이체크() throws CustomException {
        String testCase1 = "pobi";
        String testCase2 = "soree";
        String testCase3 = "pobiiii";
        String testCase4 = "soreeeeeeee";

        assertThat(validationRegularExpress.isValidationNameLengthExpress(testCase1)).isTrue();

        assertThat(validationRegularExpress.isValidationNameLengthExpress(testCase2)).isTrue();

        assertThatThrownBy(() -> validationRegularExpress.isValidationNameLengthExpress((testCase3))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 이름의 길이를 초과하였습니다.");

        assertThatThrownBy(() -> validationRegularExpress.isValidationNameLengthExpress((testCase4))).isInstanceOf(CustomException.class)
                .hasMessageContaining("[ERROR] 이름의 길이를 초과하였습니다.");
    }
}