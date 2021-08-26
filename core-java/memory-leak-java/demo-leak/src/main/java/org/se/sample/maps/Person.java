package org.se.sample.maps;

public class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (o == this) return true;
//        if (!(o instanceof Person)) {
//            return false;
//        }
//        Person person = (Person) o;
//        return person.name.equals(name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = 17;
//        result = 31 * result + name.hashCode();
//        return result;
//    }
}
 // equals / hash codes
//Size: 290,455,552 B
//Used: 8,318,584 B
//Max: 734,003,200 B


// without equals / hash code
// Size: 251,133,952 B
// Used: 21,969,120 B
// Max: 734,003,200 B

