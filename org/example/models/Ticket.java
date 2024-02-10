package org.example.models;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString

public class Ticket {
    private static int id_gen = 1;
    private int id;
    private String name;
    private int price;

    public void setId(int id) {
        this.id = id_gen++;
    }
    public Ticket(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
