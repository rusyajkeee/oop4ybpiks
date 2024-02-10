package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;


@ToString
@AllArgsConstructor

@Setter

@Getter
public class User {

    private static int id_gen = 1;
    private int id;
    private String login;
    private String password;

    ArrayList<Ticket> myTickets = new ArrayList<>();

    public User(String login, String password) {
        this.id = id_gen++;
        this.login = login;
        this.password = password;
    }
    public void printMyTickets(){
        System.out.println("Your tickets are: " + myTickets.toString());
    }
    public void buyTicket(Ticket tck){
        this.myTickets.add(tck);
        System.out.println("User " + login +" bought: " + tck);
        printMyTickets();
        System.out.println("Buy more!!!!!");
    }


}