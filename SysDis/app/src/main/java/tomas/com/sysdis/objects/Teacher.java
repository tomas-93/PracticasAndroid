package tomas.com.sysdis.objects;

/**
 * Created by Tomas on 04/10/2015.
 */
public class Teacher
{
    private long number;
    private String nameTeacher, lastName, race, level, email, phone;

    public Teacher(long number, String name, String lastName, String race,
                   String level, String email, String phone)
    {
        this.number = number;
        this.phone = phone;
        this.nameTeacher = name;
        this.lastName = lastName;
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
    public String getNameTeacher()
    {
        return this.nameTeacher;
    }
    public String getLastName()
    {
        return this.lastName;
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
