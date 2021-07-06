package sam.rus.MyHttpServer.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import sam.rus.MyHttpServer.model.JsonConvert;

import java.io.IOException;
import java.io.StringWriter;

public final class ToJSON<T> {
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
    }

    public String convertToJson(T obj) {
        String result = "";
        try (
                StringWriter resultJsonString = new StringWriter();
        ) {
            objectMapper.writeValue(resultJsonString, obj);
            result = resultJsonString.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public JsonConvert convertToObject(String json, JsonConvert type) {
        JsonConvert result = null;
        try {
            result = objectMapper.readValue(json, type.getClass());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
