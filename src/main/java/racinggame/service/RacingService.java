package racinggame.service;

import nextstep.utils.Console;
import nextstep.utils.Randoms;
import racinggame.exception.CustomException;
import racinggame.model.RacingModel;
import racinggame.validate.ValidationRegularExpress;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacingService {

    private int maxValue = 0;

    public void play() {
        RacingModel players = userInput();
        processRacing(players);
    }

    /**
     * '시도할 회수는 몇회인가요?' 의 반복
     *
     * @param racingModel
     */
    private void processRacing(RacingModel racingModel) {
        for (int i = 0; i < racingModel.getRacingCount(); i++) {
            processRacingPlayers(racingModel);
            userOutput(racingModel);
        }
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
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        if (randomNumber >= 4) {
            racingModel.add(player, 1);
        }
    }

    /**
     * 사용자 입력
     *
     * @return
     */
    private RacingModel userInput() {

        Map<String, Integer> result = new HashMap<>();
        int playCount = 0;

        try {
            System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
            String inputPlayer = Console.readLine().trim();
            ValidationRegularExpress validationRegularExpress = new ValidationRegularExpress();
            boolean validate = validationRegularExpress.validationNameRegularExpress(inputPlayer);

            String[] split = inputPlayer.split(",");
            if (validate) {
                for (int i = 0; i < split.length; i++) {
                    result.put(split[i], 0);
                }
            }

            System.out.println("시도할 회수는 몇회인가요?");
            String inputCount = Console.readLine().trim();
            validate = validationRegularExpress.validationNumberRegularExpress(inputCount);
            if (validate) {
                playCount = Integer.parseInt(inputCount);
            }

        } catch (CustomException e) {
            e.printStackTrace();
            userInput();
        } finally {
            return RacingModel.input(result, playCount);
        }
    }

    private void userOutput(RacingModel racingModel) {
        StringBuffer sb = new StringBuffer();
        for (String player : racingModel.getPlayers().keySet()) {
            int progress = racingModel.getPlayers().get(player);
            sb.append(userOutProgress(player, progress));
        }
        System.out.println(sb.toString());
    }

    private String userOutProgress(String player, int progress) {
        String result = "";
        for (int i = 0; i < progress; i++) {
            result += "-";
        }

        maxValue = Math.max(result.length(), maxValue);
        return player + " : " + result + "\r\n";
    }

    private void userOutResult(RacingModel racingModel) {
        String result = "최종 우승자는 ";
        List<String> resultMember = new ArrayList<>();
        for (String player : racingModel.getPlayers().keySet()) {
            int progress = racingModel.getPlayers().get(player);
            userMaxMember(player,progress, resultMember);

//            result += userMaxValueChecker(player, progress);
//            result += ",";
        }

        String resultSub = userMaxMemberToString(resultMember);

        result = result + resultSub + " 입니다.";
        System.out.println(result);
    }

    private List<String> userMaxMember(String player, int playerValue, List<String> resultMember) {
        if (maxValue == playerValue) {
            resultMember.add(player);
        }
        return resultMember;
    }

    private String userMaxMemberToString (List<String> resultMember){
        String result = "";
        for (int i =0; i< resultMember.size()-1; i++){
            result += resultMember.get(i) +",";
        }
        result += resultMember.get(resultMember.size()-1);
        return result;
    }

    private String userMaxValueChecker(String player, int playerValue) {
        if (maxValue == playerValue) {
            return player;
        }
        return "";
    }

    private String deleteComma(String result) {

        while(result.endsWith(",")){
            result = result.substring(0, result.length()-2);
        }

        return result;
    }

}
