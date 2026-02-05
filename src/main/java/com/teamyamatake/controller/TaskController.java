package com.teamyamatake.controller;

import com.teamyamatake.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    /*
     * TOP画面表示
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView("/top");
        return mav;
    }
}
