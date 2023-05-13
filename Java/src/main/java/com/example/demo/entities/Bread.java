package com.example.demo.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "breads")
public class Bread {
    @Id
    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "bakery")
    private Bakery bakery;
}
