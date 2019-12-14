package com.codenation;

import com.codenation.entity.Log;
import com.codenation.resource.LogResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
