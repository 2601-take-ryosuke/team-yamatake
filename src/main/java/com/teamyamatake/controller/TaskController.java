package com.teamyamatake.controller;

import com.teamyamatake.common.enums.TaskStatus;
import com.teamyamatake.controller.form.FilterForm;
import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    final String DATE_FORMAT = "yyyy-MM-dd";

    /*
     * TOP画面表示
     */
    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView("/top");
        List<TaskForm> tasks = taskService.findAllTask();
        FilterForm filterForm = new FilterForm();
        mav.addObject("filterForm", filterForm);
        mav.addObject("statusList", List.of(TaskStatus.values()));
        mav.addObject("tasks", tasks);
        return mav;
    }

    /*
     * 絞り込み表示
     */
    @GetMapping("/filter")
    public ModelAndView filter(
            @Validated
            FilterForm filterForm,
            BindingResult bindingResult
    ) {
        ModelAndView mav = new ModelAndView("/top");

        if(bindingResult.hasErrors()){
            return mav;
        }

        List<TaskForm> tasks = taskService.findByFilter(filterForm);

        mav.addObject("todayDate", LocalDateTime.now());
        mav.addObject("filterForm", filterForm);
        mav.addObject("statusList", List.of(TaskStatus.values()));
        mav.addObject("tasks", tasks);
        return mav;
    }

    /*
     * 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/");
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
