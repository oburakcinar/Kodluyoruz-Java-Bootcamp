package week2.assignment4;

public class Student {
    private int id;
    private Gender gender;

    public Student(int id, Gender gender) {
        this.id = id;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public Gender getGender() {
        return gender;
    }
}
