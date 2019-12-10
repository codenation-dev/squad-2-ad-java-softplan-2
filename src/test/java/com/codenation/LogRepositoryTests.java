package com.codenation;

import com.codenation.entity.Log;
import com.codenation.entity.User;
import com.codenation.repository.LogRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertNotEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LogRepositoryTests {
    @Autowired
    private LogRepository logRepository;

    @Autowired
    TestEntityManager entityManager;

    // Logs a serem usado para os testes
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

        for (Log log : list) {
            entityManager.persist(log);
        }

        return list;
    }


    @Test
    void salvarLog() {

        populateDataBase();

        Page<Log> response = logRepository.findAll(PageRequest.of(0, 9999));

        List<Log> responseList = response.getContent();

        for (int i = 0; i < populateDataBase().size(); i++) {
            assertThat(populateDataBase().get(i).equals(responseList.get(i))).isTrue();
        }
    }


    @Test
    void buscarPorOrigem() {

        String origin = "127.0.0.2";

        List<Log> db = populateDataBase();

        List<Log> filteredDb = db.stream().filter(el -> el.getOrigin().equals(origin)).collect(Collectors.toList());

        Page<Log> pageResponse = logRepository.findByOriginContainingIgnoreCase(origin, null);

        List<Log> responseList = pageResponse.getContent();

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(responseList.get(i))).isTrue();
        }

    }

    @Test
    void buscarPorLevel() {

        String level = "error";

        List<Log> db = populateDataBase();

        List<Log> filteredDb = db.stream().filter(el -> el.getLevel().equals(level)).collect(Collectors.toList());

        Page<Log> pageResponse = logRepository.findByLevelContainingIgnoreCase(level, null);

        List<Log> responseList = pageResponse.getContent();

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(responseList.get(i))).isTrue();
        }

    }

    @Test
    void buscarPorEnviroment() {

        String enviroment = "stage";

        List<Log> db = populateDataBase();

        List<Log> filteredDb = db.stream().filter(el -> el.getEnv().equals(enviroment)).collect(Collectors.toList());

        Page<Log> pageResponse = logRepository.findByLevelContainingIgnoreCase(enviroment, null);

        List<Log> responseList = pageResponse.getContent();

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(responseList.get(i))).isTrue();
        }

    }

    @Test
    void buscarPorDetail() {

        String detail = "c";

        List<Log> db = populateDataBase();

        List<Log> filteredDb = db.stream().filter(el -> el.getDetail().equals(detail)).collect(Collectors.toList());

        Page<Log> pageResponse = logRepository.findByDetailContainingIgnoreCase(detail, null);

        List<Log> responseList = pageResponse.getContent();

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(responseList.get(i))).isTrue();
        }
    }

    @Test
    void deleteById() {

        List<Log> db = populateDataBase();

        Long idToDelete = db.get(0).getId();

        //Removing the first item (the same from which we got the Id) from the db
        db.subList(0, 0).clear();

        List<Log> filteredDb = db.stream().filter(el -> !el.getId().equals(idToDelete)).collect(Collectors.toList());

        logRepository.deleteById(idToDelete);

        System.out.println(filteredDb.toString());

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(filteredDb.get(i))).isTrue();
        }
    }

    @Test
    void findById() {

        List<Log> db = populateDataBase();

        Long idToDelete = db.get(0).getId();

        List<Log> filteredDb = db.stream().filter(el -> el.getId().equals(idToDelete)).collect(Collectors.toList());

        logRepository.findById(idToDelete);

        for (int i = 0; i < filteredDb.size(); i++) {
            assertThat(filteredDb.get(i).equals(filteredDb.get(i))).isTrue();
        }
    }

    @Test
    void findByStored() {

        List<Log> db = populateDataBase();

        List<Log> logsToStore = db.subList(0, 5);
        List<Log> logsNotStored = db.stream().filter(l -> !logsToStore.contains(l)).collect(Collectors.toList());

        for (Log log : logsToStore) {
            log.setStored(true);
            logRepository.save(log);
        }

        Page<Log> responseNotStoredPage = logRepository.findAllByStored(false, null);
        List<Log> responseNotStoredList = responseNotStoredPage.getContent();

        Page<Log> responseStoredPage = logRepository.findAllByStored(true, null);
        List<Log> responseStoredList = responseStoredPage.getContent();


        for (int i = 0; i < responseNotStoredList.size(); i++) {
            assertThat(responseNotStoredList.get(i).getStored())
                    .isEqualTo(logsNotStored.get(i).getStored());
        }

        for (int i = 0; i < responseStoredList.size(); i++) {
            assertThat(responseStoredList.get(i).getStored())
                    .isEqualTo(logsToStore.get(i).getStored());
        }

    }
}
