package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name = "Ella";
    private final Parrot parrot; // field injection if @Autowired is annotated here

    @Autowired // constructor injection
    public Person(Parrot parrot) {
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parrot getParrot() {
        return parrot;
    }

//    setter injection if @Autowired is annotated here (given that parrot field is not constant)
//    public void setParrot(Parrot parrot) {
//        this.parrot = parrot;
//    }
}
