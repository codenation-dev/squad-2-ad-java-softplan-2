package com.codenation;


import com.codenation.entity.Log;
import com.codenation.resource.LogResource;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)

@DataJpaTest
public class LogTests {

    @Autowired
    LogResource logResource;

    // Logs a serem usados para os testes
    List<Log> populateDataBase() {
        List<Log> list = new ArrayList<>();
        list.add(LogsForTests.A());
        list.add(LogsForTests.B());
        list.add(LogsForTests.C());
        list.add(LogsForTests.D());
        list.add(LogsForTests.E());
        list.add(LogsForTests.F());
        list.add(LogsForTests.G());
        list.add(LogsForTests.H());
        list.add(LogsForTests.I());
        list.add(LogsForTests.J());
        list.add(LogsForTests.K());

        logResource.create(list, null);

        return list;
    }

    @Test
    void getToken() throws IOException {
        List<Log> logList = new ArrayList<>();
        logList.add(LogsForTests.A());

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost postReq = new HttpPost("http://localhost:8080/oauth/token");

        postReq.addHeader("Content-type", "application/x-www-form-urlencoded");
        postReq.addHeader("Authorization", "Basic c3F1YWQyOnNlY3JldA==");

        Header[] httpPostHeaders = postReq.getAllHeaders();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "admin@admin.com"));
        params.add(new BasicNameValuePair("password", "secret"));
        params.add(new BasicNameValuePair("grant_type", "password"));
        postReq.setEntity(new UrlEncodedFormEntity(params));


        System.out.println("Headers");
        for (Header header : httpPostHeaders) {
            System.out.println(header.toString());
        }

        System.out.println("Entities");
        System.out.println(EntityUtils.toString(postReq.getEntity(), "UTF-8"));


        CloseableHttpResponse response = httpClient.execute(postReq);

        HttpEntity httpResponse = response.getEntity();
        String responseString = EntityUtils.toString(httpResponse, "UTF-8");

        System.out.println("Response");
        System.out.println(responseString);
    }

    void salvarLog() {

        List<Log> logList = new ArrayList<>();
        logList.add(LogsForTests.A());

        logResource.create(logList, null);

        Page<Log> response = logResource.findAll(PageRequest.of(0, 999));

        List<Log> responseList = response.getContent();

        System.out.println(
                logList.get(0)
        );

        System.out.println(
                responseList.get(0)
        );

        assertThat(logList.get(0).equals(responseList.get(0))).isTrue();
    }

}
