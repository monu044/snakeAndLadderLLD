package com.example.demo.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
    private String id;
    private String name;
    private Integer currentPosition;
}
