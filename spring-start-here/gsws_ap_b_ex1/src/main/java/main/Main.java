package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        // get the bean by bean name (method name in config)
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
        System.out.println(p.getName());

//        String s = context.getBean(String.class);
//        System.out.println(s);
//
//        Integer n = context.getBean(Integer.class);
//        System.out.println(n);
    }
}
