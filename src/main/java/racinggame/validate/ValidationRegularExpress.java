package racinggame.validate;

import racinggame.exception.CustomException;

public class ValidationRegularExpress {
    private static final String patternNameInput = "^[a-z,]*$";
    private static final String patternNumberInput = "^[0-9]*$";

    /**
     * 소문자 + , 조합 체크
     * @param input
     * @return
     * @throws CustomException
     */
    public boolean isValidationNameRegularExpress(String input) throws CustomException {
        if (!input.matches(patternNameInput) || input.length() == 0) {
            throw new CustomException("[ERROR] 입력이 잘못 되었습니다.");
        }
        return true;
    }

    /**
     * 숫자 체크
     * @param input
     * @return
     * @throws CustomException
     */
    public boolean isValidationNumberRegularExpress(String input) throws CustomException {
        if (!input.matches(patternNumberInput)) {
            throw new CustomException("[ERROR] 숫자만 기입하여 주세요.");
        }
        return true;
    }

    /**
     * 플레이어 이름 길이 체크
     * @param input
     * @return
     * @throws CustomException
     */
    public boolean isValidationNameLengthRagularExpress(String input) throws CustomException {
        if (input.length() > 5){
            throw new CustomException("[ERROR] 이름의 길이를 초과하였습니다.");
        }
        return true;
    }
}
