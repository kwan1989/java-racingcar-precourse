package racinggame.model;

import java.util.Map;

public class RacingModel {

//    private String name;
//    private int status;
//
//    public String getName() {
//        return name;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public RacingModel(String name){
//        this.name = name;
//        this.status = 0;
//    }
//
//    public void add(int move){
//        this.status += move;
//    }

    private Map<String, Integer> players;
    private int racingCount;

    public Map<String, Integer> getPlayers() {
        return players;
    }

    public void setPlayers(Map<String, Integer> players) {
        this.players = players;
    }

    public int getRacingCount() {
        return racingCount;
    }

    public RacingModel(Map<String, Integer> players , int racingCount) {
        this.players = players;
        this.racingCount = racingCount;
    }

    public void add(String player, int move){
        this.players.put(player, players.get(player) + move);
    }

    public static RacingModel input(Map<String, Integer> players, int racingCount){
        return new RacingModel(players, racingCount);
    }
}
