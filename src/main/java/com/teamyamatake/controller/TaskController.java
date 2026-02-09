package com.teamyamatake.controller;

import com.teamyamatake.common.enums.TaskStatus;
import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.repository.entity.Task;
import com.teamyamatake.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
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
        List<TaskForm> tasks = taskService.findAllTask();
        mav.addObject("todayDate", LocalDate.now());
        mav.addObject("statusList", List.of(TaskStatus.values()));
        mav.addObject("tasks", tasks);
        return mav;
    }

    /*
     * 新規タスク追加画面表示
     */
    @GetMapping("/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        TaskForm taskForm = new TaskForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", taskForm);
        return mav;
    }

    /*
     * 新規タスク追加処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@Validated @ModelAttribute("formModel") TaskForm taskForm,
                                   BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("/new");
        }

        taskService.saveTask(taskForm);

        return new ModelAndView("redirect:/");
    }
}
