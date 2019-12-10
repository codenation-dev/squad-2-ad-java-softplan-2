package com.codenation;

import com.codenation.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;


@DataJpaTest
@RunWith(SpringRunner.class)
class CentralErrosApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        assertNotEquals(null, user.getId(), "Id não deve ser nulo");

    }

}

