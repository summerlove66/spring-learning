package com.park.model;

public class Student  implements Learning {
    private  Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void learn() {
        System.out.println(this.person.getName() +" learning");
    }

    @Override
    public String toString() {
        return "Student{" +
                "person=" + person +
                '}';
    }
}
