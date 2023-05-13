package com.example.demo.requests;

import com.example.demo.entities.Bakery;
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
public class GetBakeriesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class BakeryEntry{
        private String name;
    }

    @Singular
    private List<BakeryEntry> bakeries;

    public static Function<Collection<Bakery>, GetBakeriesResponse> entityToDtoMapper()
    {
        return bakeries -> {
            GetBakeriesResponseBuilder response = GetBakeriesResponse.builder();
            bakeries.stream().map(bakery -> BakeryEntry.builder().name(bakery.getName()).build())
                    .forEach(response::bakery);
            return response.build();
        };
    }
}
