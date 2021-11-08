package com.se.sample;


import java.util.ArrayList;
import java.util.List;

public class UniversityGroup {
    public List<String> students;

    public UniversityGroup() {
        students = new ArrayList<>();
        students.add("Сергей Фрединский");
        students.add("Виталий Правдивый");
        students.add("Максим Козыменко");
        students.add("Наталия Андрющенко");
        students.add("Ира Величенко");
        students.add("Николай Соболев");
        students.add("Снежана Слободенюк");
    }

    public void exclude(String excludedStudent) {

//        List<String> res = new ArrayList<>();
//        for (String student : students) {
//            if (!student.equals(excludedStudent)) {
//                //students.remove(student);
//                res.add(student);
//            }
//        }
//
//        students.clear();
//        students.addAll(res);

        List<String> toRemove = new ArrayList<String>();
        for(String a: students){
            if(a.equals(excludedStudent)){
                toRemove.add(a);
            }
        }

        students.removeAll(toRemove);
    }

    public static void main(String[] args) {
        UniversityGroup universityGroup = new UniversityGroup();
        universityGroup.exclude("Виталий Правдивый");

        universityGroup.students.forEach(System.out::println);
    }
}