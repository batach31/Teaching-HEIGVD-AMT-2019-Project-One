package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer
{
    private long customer_id;
    private String customer_pseudo;
    private String firstname;
    private String lastname;
    private int age;
    private String customer_pw;

}
