package ch.heigvd.amt.projectone.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer
{
    private long customer_id;
    private String customer_pseudo;
    private String firstname;
    private String lastname;
    private int age;
    private String customer_pw;

    public Customer(long customer_id, String customer_pseudo, String firstname, String lastname, int age, String customer_pw) {
        this.customer_id = customer_id;
        this.customer_pseudo = customer_pseudo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.customer_pw = customer_pw;
    }
}
