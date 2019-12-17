package com.codenation.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    static public class LogDTO {
        private Long id;
        private String title;
        private String level;
        private String detail;
        private String createdAt;
        private String origin;
        private String token;
        private String generatedBy;
        private String environment;
        private boolean stored;
        private int events;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    static class Pageable {

        @Getter
        @Setter
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        @ToString
        static class Sort {
            private boolean sorted;
            private boolean unsorted;
            private boolean empty;
        }

        private int pageNumber;
        private int pageSize;
        private int offset;
        private boolean paged;
        private boolean unpaged;
    }

    List<LogDTO> content;

    Pageable pageable;

    int totalElements;
    int totalPages;
    boolean last;
    boolean first;

    Pageable.Sort sort;

    int numberOfElements;
    int size;
    int number;
    boolean empty;
}

