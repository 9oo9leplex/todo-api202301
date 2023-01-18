package com.example.todo.todoapi.repository;

import com.example.todo.todoapi.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    // 완료되지 않은 할 일들만 조회
//    @Query("select t from TodoEntity t where t.done = 0")
//    List<TodoEntity> findNotYetTodos();

    Optional<TodoEntity> findById(String todoId);
}
