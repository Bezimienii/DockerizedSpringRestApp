package com.example.demo.requests;

import com.example.demo.entities.Bakery;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBakeryResponse {
    private String name;

    private int numberclients;

    private int salesOfLastMonth;

    public static Function<Bakery, GetBakeryResponse> entityToDtoMapper()
    {
        return bakery -> GetBakeryResponse.builder().name(bakery.getName())
                .numberclients(bakery.getNumberclients())
                .salesOfLastMonth(bakery.getSalesOfLastMonth())
                .build();
    }
}
