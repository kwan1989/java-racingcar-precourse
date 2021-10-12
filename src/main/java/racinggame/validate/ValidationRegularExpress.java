package racinggame.validate;

import racinggame.exception.CustomException;

public class ValidationRegularExpress {
    private static final String patternNameInput = "^[a-z,]*$";
    private static final String patternNumberInput = "^[0-9]*$";

    public boolean validationNameRegularExpress(String input) throws CustomException {
        if (!input.matches(patternNameInput) || input.length() == 0) {
            throw new CustomException("[ERROR] 입력이 잘못 되었습니다.");
        }
        return true;
    }

    public boolean validationNumberRegularExpress(String input) throws CustomException {
        if (!input.matches(patternNumberInput)) {
            throw new CustomException("[ERROR] 숫자만 기입하여 주세요.");
        }
        return true;
    }
}
