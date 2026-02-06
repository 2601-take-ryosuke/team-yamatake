package com.teamyamatake.controller;

import com.teamyamatake.common.enums.TaskStatus;
import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.repository.entity.Task;
import com.teamyamatake.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
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
     * 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return new ModelAndView("redirect:/");
    }
}
