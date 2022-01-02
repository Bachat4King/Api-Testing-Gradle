import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromJSONFile {

    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();

        FileReader reader = new FileReader("/Users/bastian.silva/Desktop/Java/first-gradle-proyect/src/test/java/tests/data/data.json");

        Object ob = jsonparser.parse(reader);
        JSONObject jsonObject = (JSONObject) ob;
        // un poco de texto para eliminar
        System.out.println(jsonObject.get("data"));

        JSONArray jsonArray = (JSONArray) jsonObject.get("data");
        System.out.println(jsonArray);

        for (Object o : jsonArray) {
            System.out.println(o);
        }
    }

}