package racinggame.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import racinggame.model.RacingModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class RacingServiceTest {

    RacingService racingService;
    RacingModel racingModel;

    @BeforeEach
    void init(){
        racingService = new RacingService();
    }

    @Test
    void TC1_레이싱테스트() {
        Map<String, Integer> player = new HashMap<>();
        player.put("pobi", 0);
        player.put("kwan", 0);
        player.put("soree", 0);
        player.put("sara", 0);
        player.put("anna", 0);
        int playCount = 5;
        racingModel = new RacingModel(player, playCount);

        racingService.processRacing(racingModel);
    }

    @Test
    void TC2_우승자나열(){
        List<String> fastPlayer = new ArrayList<String>();
        fastPlayer.add("pobi");
        fastPlayer.add("kwan");
        fastPlayer.add("soree");

        assertEquals("pobi,kwan,soree",racingService.userMaxMemberToString(fastPlayer));;
    }
}