package com.codenation;

import com.codenation.Entity.GetResponse;
import com.codenation.Entity.OAuthResponse;
import com.codenation.entity.Log;
import com.codenation.repository.LogRepository;
import com.codenation.service.LogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LogTests {

    String token;

    @Autowired
    LogService logService;

    public String getToken() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postReq = new HttpPost("http://localhost:8080/oauth/token");

        postReq.addHeader("Content-type", "application/x-www-form-urlencoded");
        postReq.addHeader("Authorization", "Basic c3F1YWQyOnNlY3JldA==");

//        Header[] httpPostHeaders = postReq.getAllHeaders();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "admin@admin.com"));
        params.add(new BasicNameValuePair("password", "secret"));
        params.add(new BasicNameValuePair("grant_type", "password"));
        postReq.setEntity(new UrlEncodedFormEntity(params));

//        System.out.println("Headers");
//        for (Header header : httpPostHeaders) {
//            System.out.println(header.toString());
//        }

        CloseableHttpResponse response = httpClient.execute(postReq);

        HttpEntity httpResponse = response.getEntity();
        String responseString = EntityUtils.toString(httpResponse, "UTF-8");

        OAuthResponse objResponse = new ObjectMapper().readValue(responseString, OAuthResponse.class);

        response.close();

        return objResponse.getAccess_token();
    }

    void saveListofLogsInDB(List<Log> logList) throws IOException {

        String token = getToken();

        String logListUInJson = new ObjectMapper().writeValueAsString(logList);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postReq = new HttpPost("http://localhost:8080/api/v1/log");

        postReq.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        postReq.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        StringEntity body = new StringEntity(logListUInJson, ContentType.APPLICATION_JSON);
        postReq.setEntity(body);

        CloseableHttpResponse httpResponse = httpClient.execute(postReq);

//        HttpEntity httpEntity = httpResponse.getEntity();

//        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
//        System.out.println(responseString);
//        System.out.println("");
//        System.out.println("");
//        System.out.println("Se retornou erro ao salver, está aqui acima. Resposta de 'OK' é vazio.");

        httpResponse.close();

        this.token = token;
    }

    void cleanDB() throws IOException {

        List<Log> logList = logService.findAll(null).getContent();

        for (Log log : logList)
            logService.deleteById(log.getId());

        List<Log> remainingLogs = logService.findAll(null).getContent();

//        System.out.println("");
//        System.out.println("Iniciando remoção dos dados do DB");
//        System.out.println("");
//
//        for (Log log : remainingLogs) {
//            System.out.println(log.getId());
//        }

    }


    @Test
    void salvarLogELerBancoViaHTTP() throws IOException {
        List<Log> logList = new ArrayList<>();
        logList.add(LogsForTests.A());

        saveListofLogsInDB(logList);

        String token = this.token;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getReq = new HttpGet("http://localhost:8080/api/v1/log");

        getReq.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        getReq.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        CloseableHttpResponse httpGetResponse = httpClient.execute(getReq);

        HttpEntity httpGetEntity = httpGetResponse.getEntity();
        String getResponseString = EntityUtils.toString(httpGetEntity, "UTF-8");
//        System.out.println(getResponseString);
//        System.out.println("String da resposta está aqui em cima");
//        System.out.println("");

        httpGetResponse.close();

        GetResponse responseInGetResponse = new ObjectMapper().readValue(getResponseString, GetResponse.class);

        GetResponse.LogDTO log = responseInGetResponse.getContent().get(0);

//        System.out.println("");
//        System.out.println("");
//        System.out.println(log);
//        System.out.println("");
//        System.out.println("");

        assertThat(log.getTitle()).isEqualTo(logList.get(0).getTitle());


        cleanDB();

    }

    //    @Test
    void buscarPorEnvironment() throws IOException {
        List<Log> logList = new ArrayList<>();
        logList.add(LogsForTests.A());
        logList.add(LogsForTests.B());
        logList.add(LogsForTests.C());
        logList.add(LogsForTests.D());
        logList.add(LogsForTests.E());
        logList.add(LogsForTests.F());
        logList.add(LogsForTests.G());
        logList.add(LogsForTests.H());
        logList.add(LogsForTests.I());
        logList.add(LogsForTests.J());
        logList.add(LogsForTests.K());

        saveListofLogsInDB(logList);

        String token = this.token;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String environment = "DEVELOPMENT";
        HttpGet getReq = new HttpGet("http://localhost:8080/api/v1/log/" + environment);

        getReq.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        getReq.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        CloseableHttpResponse httpGetResponse = httpClient.execute(getReq);

        HttpEntity httpGetEntity = httpGetResponse.getEntity();
        String getResponseString = EntityUtils.toString(httpGetEntity, "UTF-8");

        httpGetResponse.close();

        GetResponse responseInGetResponse = new ObjectMapper().readValue(getResponseString, GetResponse.class);
        List<GetResponse.LogDTO> responseLogList = responseInGetResponse.getContent();

        List<Log> filteredLogList = logList.stream().filter(log -> log.getEnvironment().toString().contains(environment)).collect(Collectors.toList());

        System.out.println(getResponseString);

//        cleanDB(
//                Arrays.asList(log)
//        );
    }

    @Test
    void storeAndUnstoreById() throws IOException {

        List<Log> logList = new ArrayList<>();
        logList.add(LogsForTests.A());
        logList.add(LogsForTests.B());
        logList.add(LogsForTests.C());

        saveListofLogsInDB(logList);

        String token = this.token;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getReq = new HttpGet("http://localhost:8080/api/v1/log");

        getReq.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        getReq.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        CloseableHttpResponse httpGetResponse = httpClient.execute(getReq);

        HttpEntity httpGetEntity = httpGetResponse.getEntity();
        String getResponseString = EntityUtils.toString(httpGetEntity, "UTF-8");
//        System.out.println(getResponseString);
//        System.out.println("String da resposta está aqui em cima");
//        System.out.println("");

        httpGetResponse.close();

        GetResponse responseInGetResponse = new ObjectMapper().readValue(getResponseString, GetResponse.class);

        List<GetResponse.LogDTO> patchedLogList = responseInGetResponse.getContent();


        String patchedIdsListInString = "";

        for (int i = 0; patchedLogList.size() > i; i++) {
            patchedIdsListInString += patchedLogList.get(i).getId().toString();
            if (i + 1 != patchedLogList.size()) {
                patchedIdsListInString += ",";
            }
        }


        HttpPatch httpPatch = new HttpPatch("http://localhost:8080/api/v1/log/store/" + patchedIdsListInString);

        httpPatch.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        httpPatch.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        CloseableHttpResponse httpDelResponse = httpClient.execute(httpPatch);

        httpDelResponse.close();


        CloseableHttpResponse httpGetResponse2 = httpClient.execute(getReq);

        HttpEntity httpGetEntity2 = httpGetResponse2.getEntity();
        String getResponseString2 = EntityUtils.toString(httpGetEntity2, "UTF-8");

        httpGetResponse2.close();

        GetResponse responseInGetResponse2 = new ObjectMapper().readValue(getResponseString2, GetResponse.class);

        assertThat(responseInGetResponse2.getContent().size()).isEqualTo(0);

        cleanDB();
    }


}
