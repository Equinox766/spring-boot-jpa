package org.equinox.springbootjpa;

import org.equinox.springbootjpa.entities.Person;
import org.equinox.springbootjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

    @Autowired
    private PersonRepository personRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        create();
    }

    public void create(){
        Person person = personRepository.save(new Person(null,"Rosa","Meltrozo","Go "));
        System.out.println(person);
    }

    public void findOne() {
//        personRepository.findOne(3L).ifPresent(System.out::println);
/*        personRepository.findOneName("John").ifPresent(System.out::println);*/
//        personRepository.findOneLikeName("mAR").ifPresent(System.out::println);/**/
        personRepository.findByNameContaining("Mar").ifPresent(System.out::println);

    }

    public void find() {
        //        List<Person> people = personRepository.buscarByProgrammingLanguage("Java", "Maria");
        List<Person> people = personRepository.findByProgrammingLanguageAndName("Java", "Maria");

        people.forEach(System.out::println);

        List<Object[]> peopleData = personRepository.obtenerDataPersona();
        peopleData.stream().forEach(person -> System.out.println(person[0] + " esta aprendiendo " + person[1]));
    }
}
