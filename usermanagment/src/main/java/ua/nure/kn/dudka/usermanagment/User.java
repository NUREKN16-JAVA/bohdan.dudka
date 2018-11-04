package ua.nure.kn.dudka.usermanagment;

import java.time.LocalDate;
/**
* Class User contains information about user
* */
public class User {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;


    User(long id, String firstName, String lastName, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
    * @return user's full name in format "First name, Last name"
    * */
    String getFullName() {
        return lastName + ", " + firstName;
    }

    /**
     * This method expects correct birth date established in the past
     * @return user's age in years
     */
    long getAge() {
        LocalDate date = LocalDate.now();
        int age = date.getYear() - dateOfBirth.getYear();
        if (date.getMonthValue() < dateOfBirth.getMonthValue() ||
                (date.getMonthValue() == dateOfBirth.getMonthValue() && date.getDayOfMonth() < dateOfBirth.getDayOfMonth())) {
            --age;
        }

        return age;
    }
}
