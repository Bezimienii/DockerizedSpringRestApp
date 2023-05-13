package com.example.demo.requests;

import com.example.demo.entities.Bakery;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutBakeryRequest {
    private String name;

    private int numberclients;

    private int salesOfLastMonth;

    public static BiFunction<Bakery, PutBakeryRequest, Bakery> dtoToEntityMapper()
    {
        return (bakery, request) ->
        {
            bakery.setName(request.getName());
            bakery.setNumberclients(request.getNumberclients());
            bakery.setSalesOfLastMonth(request.getSalesOfLastMonth());
            return bakery;
        };
    }
}
