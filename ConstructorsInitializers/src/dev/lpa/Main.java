package dev.lpa;

import external.Child;

public class Main {
    public static void main(String[] args) {
        Parent parent = new Parent("Albert", "10/10/2020", 3);
        Child child = new Child();

        System.out.println("Parent -> " + parent.toString());
        System.out.println("Child -> " + child.toString());

        Person person = new Person("Gustavo", "21-06-2005");
        System.out.println(person);

        Generation g = Generation.GREATEST_GENERATION;

    }
}