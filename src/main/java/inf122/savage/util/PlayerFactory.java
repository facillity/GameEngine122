package inf122.savage.util;

import inf122.savage.engine.*;
import org.json.simple.JSONObject;

public abstract class PlayerFactory {

    public static Player loadPlayer(String username, String gameName){
        JSONObject data = JSONPlayerSaver.loadFile();
        Player p = new Player(username);
        if(data.containsKey(username)){
            JSONObject playerData = (JSONObject) data.get(username);
            if(playerData.containsKey(gameName)){
                int wins = ((Long) playerData.get(gameName)).intValue();
                p.setWinCount(wins);
            }
        }
        return p;
    }

    public static void savePlayer(Player p, String gameName){
        JSONObject data = JSONPlayerSaver.loadFile();
        if(data.containsKey(p.getName())){
            JSONObject playerData = (JSONObject) data.get(p.getName());
            playerData.put(gameName, p.getWinCount());
            data.put(p.getName(), playerData);
            JSONPlayerSaver.saveFile(data);
        }
    }

    public static void main(String[] args){
        Player p = PlayerFactory.loadPlayer("facility", "othello");
        p.addWin();
        PlayerFactory.savePlayer(p, "othello");
    }
}
