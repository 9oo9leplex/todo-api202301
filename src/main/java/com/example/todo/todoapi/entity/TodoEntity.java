package com.example.todo.todoapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "tbl_todo")
public class TodoEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String todoId; // 랜덤 문자열
    @Column(nullable = false, length = 30)
    private String title; // 제목
    private boolean isDone; // 일정 완료 여부
    @CreationTimestamp
    private LocalDateTime createDate; // 등록 시간
}
