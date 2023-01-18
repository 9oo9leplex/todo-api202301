package com.example.todo.todoapi.dto.rs;

import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoListRsDTO {

    private String error;
    private List<TodoDetailRsDTO> todos;
}
