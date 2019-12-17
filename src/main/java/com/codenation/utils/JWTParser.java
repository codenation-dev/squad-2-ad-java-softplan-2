package com.codenation.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

public class JWTParser {

  public Map<String, Object> parseToken(String token) {
    String payload;
    payload = token.split("\\.")[1];

    Base64 base64 = new Base64();
    String decodedPayload = new String(base64.decode(payload.getBytes()));

    JsonParser jsonParser = JsonParserFactory.getJsonParser();
    return jsonParser.parseMap(decodedPayload);
  }
}
