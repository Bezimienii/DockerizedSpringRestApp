package com.example.demo.requests;

import com.example.demo.entities.Bread;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetBreadsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class BreadEntry{
        private String name;
    }

    @Singular
    private List<BreadEntry> breads;

    public static Function<Collection<Bread>, GetBreadsResponse> entityToDtoMapper()
    {
        return breads -> {
            GetBreadsResponseBuilder response = GetBreadsResponse.builder();
            breads.stream().map(bread -> BreadEntry.builder().name(bread.getName()).build()).forEach(response::bread);
            return response.build();
        };
    }
}
