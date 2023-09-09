import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperacionController {

    @RequestMapping(value = "/operacion", method = RequestMethod.POST)
    public ResponseEntity<JsonObject> getOperacion(@RequestBody String body) throws Exception {
        JsonObject requestBody = new JsonParser().parse(body).getAsJsonObject();
        String marca = requestBody.get("marca").getAsString();
        double importe = requestBody.get("importe").getAsDouble();

        JsonObject responseBody = new JsonObject();
        responseBody.addProperty("marca", marca);
        responseBody.addProperty("importe", importe);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
