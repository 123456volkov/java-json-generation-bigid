import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.JSONutil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class Main {
    public static void main(String[] args) throws IOException {


        int index = 0;
        int prefix = 1;
        String doc = "index_" + prefix;

        Response response = null;
        RestAssured.baseURI = "http://10.2.0.138:9200";
        //BufferedWriter writer = new BufferedWriter(new FileWriter("mockData.json"));
        String json = "";

        JSONutil jsoNutil = new JSONutil(5);
        for (int i = 0; i <= 10000; i++) {

            if (index == 31){
                index = 0;
                ++prefix;
                doc = "index_" + prefix;
            }

            System.out.println("Number :" + i);

            json = jsoNutil.createNewJSON();

            response = given()
                   .contentType("application/json")
                   .header("Authorization", "Basic ZWxhc3RpYzplbGFzdGlj")
                   .body(json)
                   .post(String.format("/%s/primary", doc));

            System.out.println("Status Code :" + response.getStatusCode());
            System.out.println("Response as String :" + response.asString());

            ++index;
            //writer.write(jsoNutil.createNewJSON());
            //writer.write("\n");
        }

        //writer.close();
    }
}
