package com.example.demo.requests;

import com.example.demo.entities.Bakery;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostBakeryRequest {

    private String name;
    private int numberclients;
    private int salesOfLastMonth;

    public static Function<PostBakeryRequest, Bakery> dtoToEntityMapper()
    {
        return request -> Bakery.builder().name(request.getName())
                .numberclients(request.getNumberclients())
                .salesOfLastMonth(request.getSalesOfLastMonth())
                .build();
    }
}
