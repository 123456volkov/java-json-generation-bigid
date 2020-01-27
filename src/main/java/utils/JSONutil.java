package utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.andreinc.mockneat.MockNeat;
import java.util.concurrent.ThreadLocalRandom;

public class JSONutil {

    private int countOfMainKeys;
    private MockNeat mockNeat = MockNeat.threadLocal();
    String json;

    public JSONutil(int countOfMainKeys){
        this.countOfMainKeys = countOfMainKeys;
    }

    private String createPair() {
        String keyValuePair = String.format(" %s : %s ", "_"+mockNeat.strings().size(5).get(), mockNeat.strings().size(15).get() );
        return  keyValuePair;
    }

    private String createInsertedMass(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s : [", "_"+mockNeat.strings().size(5).get()));
        int rand = ThreadLocalRandom.current().nextInt(1,10);
        for (int i = 0; i <= rand; i++){
            stringBuilder.append(mockNeat.strings().get());
            if (i < rand)
                stringBuilder.append(",");
        }
        stringBuilder.append("]");

        return  stringBuilder.toString();
    }

    private String createInsertedObject(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%s : {", "_"+mockNeat.strings().size(5).get()));
        int rand = ThreadLocalRandom.current().nextInt(1,5);
        for (int i = 0; i <= rand; i++){
            if (i%4 == 0){
                stringBuilder.append(createInsertedMass());
            }
            else{
                stringBuilder.append(createPair());
            }
            if (i < rand)
                stringBuilder.append(",");
        }
        stringBuilder.append("},");

        return  stringBuilder.toString();
    }

    public String createNewJSON(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        for (int i = 0; i <= countOfMainKeys; i++){
            if (i%4 == 0) {
                stringBuilder.append(createInsertedObject());
            }
            else {
                stringBuilder.append(createPair());
                stringBuilder.append(",");
            }
        }

        stringBuilder.append(String.format("_name : \"%s\",", mockNeat.names().full().get()));
        stringBuilder.append(String.format("_city : \"%s\",", mockNeat.cities().capitals().get()));
        stringBuilder.append(String.format("_ip : \"%s\",", mockNeat.ipv4s().get()));
        stringBuilder.append(String.format("_email : \"%s\",", mockNeat.emails().get()));
        stringBuilder.append(String.format("_login : \"%s\"", mockNeat.names().first().get().toUpperCase()));
        stringBuilder.append("}");

        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        json = stringBuilder.toString();
        System.out.println(json);
        return jsonObject.toString();
    }
}
