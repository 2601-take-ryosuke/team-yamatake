package com.teamyamatake.controller.form;

import com.teamyamatake.common.enums.TaskStatus;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskForm {

    private int id;
    @Pattern(regexp = "^(?s)(?![\\s　]*$).+", message = "タスクを入力してください")
    @Size(max = 140, message = "タスクは140文字以内で入力してください")
    private String content;
    private TaskStatus status;
    @NotNull(message = "期限を設定してください")
    @DateTimeFormat(pattern = "uuuu-MM-dd HH:mm:ss.SSS")
    @FutureOrPresent(message = "無効な日付です")
    private LocalDateTime limitDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}