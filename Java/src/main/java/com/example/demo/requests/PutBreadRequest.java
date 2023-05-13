package com.example.demo.requests;

import com.example.demo.entities.Bread;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutBreadRequest {

    private String name;
    private int quantity;

    public static BiFunction<Bread, PutBreadRequest, Bread> dtoToEntityMapper()
    {
        return (bread, request) ->
        {
            bread.setName(request.getName());
            bread.setQuantity(request.getQuantity());
            return bread;
        };
    }

}
