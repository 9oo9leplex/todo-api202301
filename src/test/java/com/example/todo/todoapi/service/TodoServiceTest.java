package com.example.todo.todoapi.service;

import com.example.todo.todoapi.dto.rq.TodoCreateRqDTO;
import com.example.todo.todoapi.dto.rq.TodoModifyRqDTO;
import com.example.todo.todoapi.dto.rs.TodoDetailRsDTO;
import com.example.todo.todoapi.dto.rs.TodoListRsDTO;
import com.example.todo.todoapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Commit
class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @BeforeEach
    void beforeInsert(){

        TodoCreateRqDTO dto1 = TodoCreateRqDTO.builder().title("저녁 장보기").build();
        TodoCreateRqDTO dto2 = TodoCreateRqDTO.builder().title("식물 물주기").build();
        TodoCreateRqDTO dto3 = TodoCreateRqDTO.builder().title("음악 감상하기").build();

        todoService.create(dto1);
        todoService.create(dto2);
        todoService.create(dto3);
    }

    @Test
    @DisplayName("새로운 할 일을 등록하면 생성되는 리스트는 할 일이 4개 들어있어야 한다.")
    void createTest(){
        // given
        TodoCreateRqDTO newTodo = TodoCreateRqDTO.builder()
                .title("새로운 할일~~")
                .build();
        // when
        TodoListRsDTO rsDTO = todoService.create(newTodo);

        // then
        List<TodoDetailRsDTO> todos = rsDTO.getTodos();
        assertEquals(4, todos.size());

        todos.forEach(System.out::println);
    }

    @Test
    @DisplayName("2번째 할 일의 제목을 수정수정으로 수종하고 할 일 완료처리를 해야한다.")
    void updateTest(){
        // given
        String newTitle = "수정수정";
        boolean newDone = true;

        TodoModifyRqDTO modifyRqDTO = TodoModifyRqDTO.builder()
                .title(newTitle)
                .isDone(newDone)
                .build();
        // when
        TodoDetailRsDTO targetTodo =
                todoService.retrieve().getTodos().get(1);

        TodoListRsDTO rsDTO = todoService.update(targetTodo.getId(), modifyRqDTO);

        // then
        assertEquals("수정수정", rsDTO.getTodos().get(1).getTitle());
        assertTrue(rsDTO.getTodos().get(1).isDone());
    }
}


























