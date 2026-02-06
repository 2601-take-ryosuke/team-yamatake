package com.teamyamatake.controller;

import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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
    @ResponseBody
    public ModelAndView addContent(@Validated @ModelAttribute("formModel") TaskForm TaskForm,
                                   BindingResult result){
        if (result.hasErrors()) {
            return new ModelAndView("/new");
        }
        taskService.saveTask(TaskForm);

        return new ModelAndView("redirect:/");
    }
}
