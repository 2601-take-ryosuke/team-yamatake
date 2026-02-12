package com.teamyamatake.controller.form;

import com.teamyamatake.common.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class FilterForm {
    final String DATE_FORMAT = "yyyy-MM-dd";

    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate since;
    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate until;
    private TaskStatus status;

    private String content;
}
