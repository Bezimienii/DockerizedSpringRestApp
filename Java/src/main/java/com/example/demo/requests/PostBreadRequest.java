package com.example.demo.requests;

import com.example.demo.entities.Bakery;
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
public class PostBreadRequest {

    private String name;
    private int quantity;

    private String bakery;

    public static Function<PostBreadRequest, Bread> dtoToEntityMapper(Function<String, Bakery> bakeryFunction)
    {
            return request -> Bread.builder().name(request.getName())
                    .quantity(request.getQuantity())
                    .bakery(bakeryFunction.apply(request.getBakery()))
                    .build();
    }
}
