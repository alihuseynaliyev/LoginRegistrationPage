package com.alinazim;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("testtest@gmail.com");
        user.setPassword("testPassword");
        user.setFirstName("test");
        user.setLastName("test");

        User savedUser = repo.save(user);
        User exsistUser = entityManager.find(User.class, savedUser.getId());
        assertThat(exsistUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void testFindUserByEmail() {
        String email = "testtest@gmail.com";
        User user = repo.findByEmail(email);
        assertThat(user).isNotNull();
    }

}
