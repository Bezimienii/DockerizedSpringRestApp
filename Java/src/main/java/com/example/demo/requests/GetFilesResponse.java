package com.example.demo.requests;

import com.example.demo.entities.File;
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
public class GetFilesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class FileEntry{
        private String name;
    }

    @Singular
    private List<FileEntry> files;

    public static Function<Collection<File>, GetFilesResponse> entityToDtoMapper()
    {
        return files -> {
            GetFilesResponse.GetFilesResponseBuilder response = GetFilesResponse.builder();
            files.stream().map(file -> GetFilesResponse.FileEntry.builder().name(file.getName()).build()).
                    forEach(response::file);
            return response.build();
        };
    }
}
