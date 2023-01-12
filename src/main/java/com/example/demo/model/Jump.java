package com.example.demo.model;

import com.example.demo.enums.JumpType;
import lombok.*;

import static com.example.demo.enums.JumpType.SNAKE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Jump {
    private Integer start;
    private Integer end;
    private JumpType type;

    public static boolean validDate(Integer start, Integer end, JumpType type) {
        if (SNAKE.equals(type)) {
            return start > end;
        }
        else {
            return end > start;
        }
    }
}
