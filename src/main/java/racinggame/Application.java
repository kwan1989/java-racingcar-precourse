package racinggame;

import racinggame.exception.CustomException;
import racinggame.service.RacingService;
import racinggame.validate.ValidationRegularExpress;

public class Application {
    public static void main(String[] args) {

        // TODO 자동차 경주 게임 구현
        RacingService racingService = new RacingService();
        racingService.play();
    }
}
