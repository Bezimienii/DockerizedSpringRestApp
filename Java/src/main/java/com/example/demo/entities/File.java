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
@Table(name = "files")
public class File {

    @Id
    private String name;

    private String type;

    @Lob
    private byte[] content;
}
