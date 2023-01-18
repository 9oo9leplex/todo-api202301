package com.example.todo.todoapi.dto.rq;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TodoModifyRqDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String title;
    private boolean isDone;

}
