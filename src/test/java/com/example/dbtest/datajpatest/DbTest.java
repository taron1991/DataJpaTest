package com.example.dbtest.datajpatest;

import com.example.dbtest.webmvctest.Person;
import com.example.dbtest.webmvctest.RepositoryLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
/*configure for a real database with @AutoConfigureTestDatabase annotation:*/
public class DbTest {

    //autowired - real injection,mockbean - mock object injection
    @Autowired
    RepositoryLayer repositoryLayer;

    @Autowired //if we use not real db
    private TestEntityManager entityManager;


    //real db
    @Test
    public void should_find_persons_if_repository_is_not_empty() {
        Iterable tutorials = repositoryLayer.findAll();

        assertThat(tutorials).isNotEmpty();
    }


    //real db
    @Test
    public void givenPersonObject_whenSave_thenReturnSavedPerson() {

        // given - setup or precondition
        Person person = new Person(1, "name", "surname", 27);

        // when - action or the testing
        Person person1 = repositoryLayer.save(person);

        // then - very output
        Assertions.assertNotNull(person1);
        Assertions.assertNotNull(person1.getId());

    }


    // real db
    @Test
    public void givenPersonId_whenfindbyId_thenReturnSavedPerson() {

        Person person = new Person(1, "name", "surname", 27);
        Person person1 = repositoryLayer.save(person);

        // when - action or the testing
        Person person2 = repositoryLayer.findById(person1.getId()).get();

        // then - very output
        Assertions.assertNotNull(person2);
    }


    // real db
    @Test
    public void givenPersonId_whenfindbyId_should_delete() {
        // when - action or the testing
        repositoryLayer.deleteById(1);


        List<Person> all = repositoryLayer.findAll();
        // then - very output
        Assertions.assertEquals(2, all.size());
    }


}
