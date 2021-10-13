package racinggame.service;

import nextstep.utils.Console;
import nextstep.utils.Randoms;
import racinggame.exception.CustomException;
import racinggame.model.RacingModel;
import racinggame.validate.ValidationRegularExpress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingService {

    /**
     * 가장 빠른 사람찾기 전역변수
     */
    private int maxValue = 0;

    /**
     * 레이싱 플레이
     *
     * @throws CustomException
     */
    public void play() throws CustomException {
        processRacing(userInput());
    }

    /**
     * '시도할 회수는 몇회인가요?' 의 반복
     * TC 접근 PUBLIC 변경.
     *
     * @param racingModel
     */
    public void processRacing(RacingModel racingModel) {
        for (int i = 0; i < racingModel.getRacingCount(); i++) {

            /* 플레이어 레이싱 */
            processRacingPlayers(racingModel);

            /* 레이싱 결과 출력*/
            userOutput(racingModel);
        }

        /* 최종 우승 출력 */
        userOutResult(racingModel);
    }

    /**
     * 플레이어 수만큼의 반복
     *
     * @param racingModel
     */
    private void processRacingPlayers(RacingModel racingModel) {
        for (String player : racingModel.getPlayers().keySet()) {
            checkMoveForward(racingModel, player);
        }
    }

    /**
     * 랜덤수와 비교하여 전진여부 체크
     *
     * @param racingModel
     * @param player
     */
    private void checkMoveForward(RacingModel racingModel, String player) {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            racingModel.add(player);
        }
    }

    /**
     * 사용자 입력
     *
     * @return RacingModel 바인딩
     */
    private RacingModel userInput() throws CustomException {
        Map<String, Integer> result = userInputDataPlayer();
        int playCount = userInputDataPlayCount();

        return RacingModel.input(result, playCount);
    }

    /**
     * 사용자 입력, 플레이어 리스트
     * @return
     * @throws CustomException
     */
    private Map<String, Integer> userInputDataPlayer() throws CustomException {
        try {
            ValidationRegularExpress validationRegularExpress = new ValidationRegularExpress();
            Map<String, Integer> result = new HashMap<>();

            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String inputPlayer = Console.readLine().trim();
            String[] split = inputPlayer.split(",");

            if (validationRegularExpress.isValidationNameRegularExpress(inputPlayer)) {
                result = userInputDataMapper(result, split);
            }

            return result;
        } catch (CustomException ex) {
            System.out.println(ex.getMessage());
            return userInputDataPlayer();
        }
    }

    /**
     * 사용자 입력, 레이싱 사이클(횟수)
     * @return
     * @throws CustomException
     */
    private int userInputDataPlayCount() throws CustomException {
        try {
            ValidationRegularExpress validationRegularExpress = new ValidationRegularExpress();
            int playCount = 0;

            System.out.println("시도할 회수는 몇회인가요?");
            String inputCount = Console.readLine().trim();

            if (validationRegularExpress.isValidationNumberRegularExpress(inputCount)) {
                playCount = Integer.parseInt(inputCount);
            }

            return playCount;
        }catch(CustomException ex){
            System.out.println(ex.getMessage());
            return userInputDataPlayCount();
        }
    }

    /**
     * Map 바인딩
     *
     * @param map
     * @param split
     * @return 맵으로 반환
     */
    private Map<String, Integer> userInputDataMapper(Map<String, Integer> map, String[] split) throws CustomException {
        for (int i = 0; i < split.length; i++) {
            isUserInputDataNameLengthValidate(split[i]);
            map.put(split[i], 0);
        }
        return map;
    }

    /**
     * 이름은 5자 이하만 가능하다.
     *
     * @param userName
     * @return
     * @throws CustomException
     */
    private boolean isUserInputDataNameLengthValidate(String userName) throws CustomException {
        ValidationRegularExpress validationRegularExpress = new ValidationRegularExpress();
        return validationRegularExpress.isValidationNameLengthExpress(userName);
    }

    /**
     * 과정 출력
     *
     * @param racingModel
     */
    private void userOutput(RacingModel racingModel) {
        StringBuffer sb = new StringBuffer();
        for (String player : racingModel.getPlayers().keySet()) {
            int progress = racingModel.getPlayers().get(player);
            sb.append(player + " : " + userOutProgress(progress) + "\r\n");
            maxValue = Math.max(progress, maxValue);
        }
        System.out.println(sb.toString());
    }

    private String userOutProgress(int progress) {
        String result = "";
        for (int i = 0; i < progress; i++) {
            result += "-";
        }
        return result;
    }

    /**
     * 결과물 출력
     *
     * @param racingModel
     */
    private void userOutResult(RacingModel racingModel) {
        StringBuilder result = new StringBuilder("최종 우승자는 ");
        List<String> resultMember = new ArrayList<>();
        for (String player : racingModel.getPlayers().keySet()) {
            userMaxMember(player, racingModel.getPlayers().get(player), resultMember);
        }

        result.append(userMaxMemberToString(resultMember)).append(" 입니다.");
        System.out.println(result);
    }

    /**
     * 가장 진행이 빠른 플레이어 찾기
     *
     * @param player
     * @param playerValue
     * @param resultMember
     * @return
     */
    private List<String> userMaxMember(String player, int playerValue, List<String> resultMember) {
        if (maxValue == playerValue) {
            resultMember.add(player);
        }
        return resultMember;
    }

    /**
     * 가장 진행이 빠른 플레이어 리스트
     *
     * @param resultMember TC를 위한 PUBLIC
     * @return 가장 빠른 레이스 플레이어 리스트 콤마로 이어붙인 String
     */
    public String userMaxMemberToString(List<String> resultMember) {
        String result = "";
        for (int i = 0; i < resultMember.size(); i++) {
            result += resultMember.get(i) + ",";
        }
        return result.replaceAll(",$", "");
    }

}
