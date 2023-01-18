package com.example.todo.todoapi.service;

import com.example.todo.todoapi.dto.rq.TodoCreateRqDTO;
import com.example.todo.todoapi.dto.rq.TodoModifyRqDTO;
import com.example.todo.todoapi.dto.rs.TodoDetailRsDTO;
import com.example.todo.todoapi.dto.rs.TodoListRsDTO;
import com.example.todo.todoapi.entity.TodoEntity;
import com.example.todo.todoapi.repository.TodoRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoDetailRsDTO findById(String todoId){

        Optional<TodoEntity> targetEntity = todoRepository.findById(todoId);

        return targetEntity
                .map(TodoDetailRsDTO::new).orElseThrow();
    }

    // 할 일 목록 조회
    public TodoListRsDTO retrieve(){

        List<TodoEntity> entityList = todoRepository.findAll();

        List<TodoDetailRsDTO> todoList = entityList.stream()
                .map(te -> new TodoDetailRsDTO(te))
                .collect(Collectors.toList());

        return TodoListRsDTO.builder()
                .todos(todoList)
                .build();
    }

    // 할 일 등록
    public TodoListRsDTO create(final TodoCreateRqDTO createRqDTO) throws ExecutionControl.RunException {


        todoRepository.save(createRqDTO.todoEntity());
        log.info("할 일이 저장되었습니다. 제목 : {}", createRqDTO.getTitle());

        return retrieve();
    }
    
    // 할 일 수정
    public TodoListRsDTO update(final String id, final TodoModifyRqDTO todoModifyRqDTO){

        Optional<TodoEntity> targetEntity = todoRepository.findById(id);

        targetEntity.ifPresent(entity -> {
            entity.setTitle(todoModifyRqDTO.getTitle());
            entity.setDone(todoModifyRqDTO.isDone());

            todoRepository.save(entity);
        });

        return retrieve();
    }

    // 할 일 삭제
    public TodoListRsDTO delete(final String id){


        try {
            todoRepository.deleteById(id);

            return retrieve();
        } catch (Exception e) {
            log.error("id가 존재하지 않아 삭제에 실패했습니다. - ID: {}, error: {}",id,e.getMessage());
            throw new RuntimeException("id가 존재하지 않아 삭제에 실패했습니다.");
        }
    }
}






















































