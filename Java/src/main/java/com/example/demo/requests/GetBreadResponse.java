package com.example.demo.requests;

import com.example.demo.entities.Bread;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBreadResponse {

    private String name;
    private int quantity;
    private String bakery;

    public static Function<Bread, GetBreadResponse> entityToDtoMapper()
    {
        return bread -> GetBreadResponse.builder().name(bread.getName()).quantity(bread.getQuantity())
                .bakery(bread.getBakery().getName()).build();
    }
}
