package com.chewzzz.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom-2.properties")
})
public class MyFirstService {

    private final MyFirstClass myFirstClass;
    @Value("${my.custom.property}")
    private String customProperty;
    @Value("${my.prop}")
    private String customPropertyFromAnotherFile;

    // Qualifier can accept self-defined qualifier name or bean name
    public MyFirstService(@Qualifier("mySecondBean") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    // method injection
//    @Autowired
//    @Qualifier("myFirstBean")
//    public void injectDependencies(MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }


    public String getCustomProperty() {
        return customProperty;
    }

    public String getCustomPropertyFromAnotherFile() {
        return customPropertyFromAnotherFile;
    }

    public String tellAStory() {
        return "the bean is saying: " + myFirstClass.sayHello();
    }
}
