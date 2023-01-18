package com.example.todo.todoapi.dto.rq;

import com.example.todo.todoapi.entity.TodoEntity;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class TodoCreateRqDTO {

    @NotBlank
    @Size(min = 2, max = 10)
    private String title;

    // dto -> entity
    public TodoEntity todoEntity(){
        return TodoEntity.builder()
                .title(this.title)
                .build();
    }
}
