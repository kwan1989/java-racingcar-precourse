package racinggame.model;

import java.util.Map;

public class RacingModel {
    private Map<String, Integer> players;
    private int racingCount;

    public Map<String, Integer> getPlayers() {
        return players;
    }

    public int getRacingCount() {
        return racingCount;
    }

    public RacingModel(Map<String, Integer> players, int racingCount) {
        this.players = players;
        this.racingCount = racingCount;
    }

    public void add(String player) {
        this.players.merge(player, 1, (e, i) -> e + i);
    }

    public static RacingModel input(Map<String, Integer> players, int racingCount) {
        return new RacingModel(players, racingCount);
    }
}
