package com.example.demo.entities;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "bakeries")
public class Bakery {
    @Id
    private String name;

    private int numberclients;

    private int salesOfLastMonth;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "bakery")
    @ToString.Exclude
    private List<Bread> breads;
}
