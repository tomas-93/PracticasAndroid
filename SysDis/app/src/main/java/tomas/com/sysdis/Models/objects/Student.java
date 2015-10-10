package tomas.com.sysdis.Models.objects;

/**
 * Created by Tomas on 04/10/2015.
 */
public class Student
{
    private long number;
    private String nameStudent, lastName, school, race, level, email, phone;

    public Student(long number, String name, String lastName, String race,
                    String school, String level, String email, String phone)
    {
        this.number = number;
        this.phone = phone;
        this.nameStudent = name;
        this.lastName = lastName;
        this.school = school;
        this.race = race;
        this.level = level;
        this.email = email;
    }
    public long getNumber()
    {
        return this.number;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getNameStudent()
    {
        return this.nameStudent;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getSchool()
    {
        return this.school;
    }
    public String getRace()
    {
        return this.race;
    }
    public String getLevel()
    {
        return this.level;
    }
    public String getEmail()
    {
        return this.email;
    }
}
