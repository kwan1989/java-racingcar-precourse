package racinggame;

import racinggame.exception.CustomException;
import racinggame.service.RacingService;

public class Application {
    public static void main(String[] args) throws CustomException {
        try {
            // TODO 자동차 경주 게임 구현
            RacingService racingService = new RacingService();
            racingService.play();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
