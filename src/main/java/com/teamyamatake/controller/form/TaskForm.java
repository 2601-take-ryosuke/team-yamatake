package com.teamyamatake.controller.form;

import com.teamyamatake.common.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskForm {

    private int id;
    private String content;
    private TaskStatus status;
    private LocalDateTime limitDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
