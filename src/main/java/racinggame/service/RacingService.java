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

    private int maxValue = 0;

    public void play() throws CustomException {
        processRacing(userInput());
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
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            racingModel.add(player);
        }
    }

    /**
     * 사용자 입력
     *
     * @return
     */
    private RacingModel userInput() throws CustomException {
        ValidationRegularExpress validationRegularExpress = new ValidationRegularExpress();
        Map<String, Integer> result = new HashMap<>();
        int playCount = 0;

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputPlayer = Console.readLine().trim();
        String[] split = inputPlayer.split(",");

        // TODO : 메소드 변경
        if (validationRegularExpress.validationNameRegularExpress(inputPlayer)) {
            for (int i = 0; i < split.length; i++) {
                result.put(split[i], 0);
            }
        }

        System.out.println("시도할 회수는 몇회인가요?");
        String inputCount = Console.readLine().trim();
        if (validationRegularExpress.validationNumberRegularExpress(inputCount)) {
            playCount = Integer.parseInt(inputCount);
        }

        return RacingModel.input(result, playCount);
    }

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

    private void userOutResult(RacingModel racingModel) {
        StringBuilder result = new StringBuilder("최종 우승자는 ");
        List<String> resultMember = new ArrayList<>();
        for (String player : racingModel.getPlayers().keySet()) {
            userMaxMember(player, racingModel.getPlayers().get(player), resultMember);
        }

        result.append(userMaxMemberToString(resultMember)).append(" 입니다.");
        System.out.println(result);
    }

    private List<String> userMaxMember(String player, int playerValue, List<String> resultMember) {
        if (maxValue == playerValue) {
            resultMember.add(player);
        }
        return resultMember;
    }

    private String userMaxMemberToString(List<String> resultMember) {
        String result = "";
        for (int i = 0; i < resultMember.size(); i++) {
            result += resultMember.get(i) + ",";
        }
        return result.replaceAll(",$", "");
    }
}
