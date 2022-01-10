package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromJSONFile {

    public static JSONObject getJson() throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();

        FileReader reader = new FileReader("../data/data.json");

        Object ob = jsonparser.parse(reader);

        return (JSONObject) ob;

    }

}