package com.chewzzz.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

    private final MyFirstClass myFirstClass;

    // Qualifier can accept self-defined qualifier name or bean name
    public MyFirstService(@Qualifier("mySecondBean") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    // setter injection
//    @Autowired
//    @Qualifier("myFirstBean")
//    public void injectDependencies(MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }

    public String tellAStory() {
        return "the bean is saying: " + myFirstClass.sayHello();
    }
}
