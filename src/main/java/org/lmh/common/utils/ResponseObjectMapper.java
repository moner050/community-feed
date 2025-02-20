package org.lmh.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lmh.common.ui.Response;

public class ResponseObjectMapper {

    private ResponseObjectMapper() {}

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Response toResponseObject(String response) {
        try{
            return mapper.readValue(response, Response.class);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String toStringResponse(Response<?> response) {
        try {
            return mapper.writeValueAsString(response);
        }
        catch (Exception e) {
            return null;
        }
    }
}
