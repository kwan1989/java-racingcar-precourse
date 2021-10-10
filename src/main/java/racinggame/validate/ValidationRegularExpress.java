package racinggame.validate;

import racinggame.exception.CustomException;

public class ValidationRegularExpress {


    private static final String patternNameInput = "^[a-z,]*$";
    private static final String patternNumberInput = "^[0-9]*$";

    public boolean validationNameRegularExpress(String input) throws CustomException {
        String regex = patternNameInput;
        boolean result = false;

        if (!input.matches(regex) || input.length() == 0) {
            throw new CustomException("[ERROR] 입력이 잘못 되었습니다.");
        }

        result = input.matches(regex);
        return result;
    }

    public boolean validationNumberRegularExpress(String input) throws CustomException {
        String regex = patternNumberInput;
        boolean result = false;

        if (!input.matches(regex)) {
            throw new CustomException("[ERROR] 숫자만 기입하여 주세요.");
        }

        result = input.matches(regex);
        return result;
    }
}
