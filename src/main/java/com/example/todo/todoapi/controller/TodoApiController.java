package com.example.todo.todoapi.controller;

import com.example.todo.todoapi.dto.rq.TodoCreateRqDTO;
import com.example.todo.todoapi.dto.rq.TodoModifyRqDTO;
import com.example.todo.todoapi.dto.rs.TodoDetailRsDTO;
import com.example.todo.todoapi.dto.rs.TodoListRsDTO;
import com.example.todo.todoapi.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    
    // 할 일 개별 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") String todoId){

        try {
            TodoDetailRsDTO found = todoService.findById(todoId);

            return ResponseEntity
                    .ok()
                    .body(found);
        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body("입력하신 ID에 해당하는 정보가 없습니다.");

        }
    }
    
    // 할 일 목록 요청
    @GetMapping
    public ResponseEntity<?> findAll(){

        try {
            TodoListRsDTO rsDTO = todoService.retrieve();

            return ResponseEntity
                    .ok()
                    .body(rsDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    //  할 일 등록 요청
    @PostMapping
    public ResponseEntity<?> createTodo(@Validated @RequestBody TodoCreateRqDTO rqDTO,
                                        BindingResult result){

        if(result.hasErrors()) {
            log.warn("DTO 검증 에러 발생 {}",  result.getFieldError());
            return ResponseEntity
                    .badRequest()
                    .body(result.getFieldError());
        }

        try {
            TodoListRsDTO rsDTO = todoService.create(rqDTO);
            return ResponseEntity
                    .ok()
                    .body(rsDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(TodoListRsDTO.builder().error(e.getMessage()));
        }
    }
    
    // 할 일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<?> modify(@PathVariable("id") String todoId, @RequestBody TodoModifyRqDTO modifyRqDTO){

        try {
            TodoListRsDTO rsDTO = todoService.update(todoId, modifyRqDTO);

            return ResponseEntity
                    .ok()
                    .body(rsDTO);
        } catch (Exception e) {

            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String todoId) {

        log.info("/api/todos/{} DELETE request!", todoId);

        if (todoId == null || todoId.equals("")) {
            return ResponseEntity
                    .badRequest()
                    .body(TodoListRsDTO.builder().error("ID를 전달해주세요"));
        }

        try {
            TodoListRsDTO rsDTO = todoService.delete(todoId);

            return ResponseEntity.ok().body(rsDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(TodoListRsDTO.builder().error(e.getMessage()));
        }
    }
}


































