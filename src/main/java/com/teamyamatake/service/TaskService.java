package com.teamyamatake.service;

import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.repository.TaskRepository;
import com.teamyamatake.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    /*
     * タスク追加
     */
    public void saveTask(TaskForm reqTask) {
        Task saveReport = setTaskEntity(reqTask);
        taskRepository.save(saveReport);
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Task setTaskEntity(TaskForm reqTask) {
        Task task = new Task();
        task.setId(reqTask.getId());
        task.setContent(reqTask.getContent());
        task.setLimitDate(reqTask.getLimitDate());
        return task;
    }
}
