
package chrysalis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.*;


public class JSONParseWrite {
    static FileReader reader;
    static FileWriter writer;
    public static void JSONWrite(JsonArray in,File dest, String name) throws FileNotFoundException, IOException
    {
        System.out.println(dest);
        
        //To write the JSON object to the file in such a way that we can parse it, we need to read it first

        JsonElement orig = JSONParse(dest);
        System.out.println("orig:" + orig);
        //now that we have the original object, we need to add the new object to it, but keep the old one there
        JsonElement working = orig;
        System.out.println("working:" + working);
        JsonArray combine = new JsonArray();
        combine.add(working);
        System.out.println("combine: " + combine);
        combine.add(in);
        System.out.println("combine: " + combine);
        writer = new FileWriter(dest);
        writer.write(combine.toString());
        writer.close();
        
    }
    
    public static JsonElement JSONParse(File read) throws FileNotFoundException, IOException
    {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        //Take the JSON array from the file and put it into the program where it's needed
        Scanner scanner = new Scanner(read);
        String jsonStr = scanner.useDelimiter("\\A").next();
        JsonElement json =   jsonParser.parse(jsonStr);
        
        return json;
    }
    
    
    
}
