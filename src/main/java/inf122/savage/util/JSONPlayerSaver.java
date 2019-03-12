package inf122.savage.util;


import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONPlayerSaver{
	private static final String filename = "player-data.json";

	public static JSONObject loadFile(){
		JSONParser parser = new JSONParser();
		JSONObject res;
		try{
			Object obj = parser.parse(new FileReader((JSONPlayerSaver.filename)));
			res = (JSONObject) obj;
			return res;
		}catch(FileNotFoundException ex){
			JSONPlayerSaver.saveFile(new JSONObject());
		}catch(ParseException|IOException ex){
			ex.printStackTrace();
			return new JSONObject();
		}
		return JSONPlayerSaver.loadFile();
	}

	public static void saveFile(JSONObject obj) {
		try (FileWriter file = new FileWriter(JSONPlayerSaver.filename)) {
			file.write(obj.toJSONString());
			file.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
