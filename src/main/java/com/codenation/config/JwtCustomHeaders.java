package com.codenation.config;

import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

public class JwtCustomHeaders extends JwtAccessTokenConverter {
  private Map<String, String> customHeaders = new HashMap<>();
  final RsaSigner signer;
  private JsonParser objectMapper = JsonParserFactory.create();

  public JwtCustomHeaders(
          Map<String, String> customHeaders,
          KeyPair keyPair) {
    super();
    super.setKeyPair(keyPair);
    this.signer = new RsaSigner((RSAPrivateKey) keyPair.getPrivate());
    this.customHeaders = customHeaders;
  }

  @Override
  protected String encode(OAuth2AccessToken accessToken,
                          OAuth2Authentication authentication) {
    String content;
    try {
      content = this.objectMapper
              .formatMap(getAccessTokenConverter()
                      .convertAccessToken(accessToken, authentication));
    } catch (Exception ex) {
      throw new IllegalStateException(
              "Nao foi possivel validar seu token", ex);
    }
    return JwtHelper.encode(
            content,
            this.signer,
            this.customHeaders).getEncoded();
  }
}
