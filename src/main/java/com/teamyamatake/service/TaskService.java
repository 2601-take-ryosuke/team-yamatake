package com.teamyamatake.service;

import com.teamyamatake.common.enums.TaskStatus;
import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.repository.TaskRepository;
import com.teamyamatake.repository.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<TaskForm> findAllTask() {
        List<Task> results = taskRepository.findAllByOrderByLimitDateDesc();
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<TaskForm> setTaskForm(List<Task> results) {
        List<TaskForm> tasks = new ArrayList<>();

        for (Task task : results) {
            TaskForm taskForm = new TaskForm();
            taskForm.setId(task.getId());
            taskForm.setContent(task.getContent());
            taskForm.setStatus(TaskStatus.getType(task.getStatus()));
            taskForm.setLimitDate(task.getLimitDate());
            taskForm.setCreatedDate(task.getCreatedDate());
            taskForm.setUpdatedDate(task.getUpdatedDate());

            tasks.add(taskForm);
        }
        return tasks;
    }

    /*
     * タスク追加
     */
    public void saveTask(TaskForm reqTask) {
        // 新規タスク作成時はステータスを未着手に設定
        reqTask.setStatus(TaskStatus.NotStarted);
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
        task.setStatus(reqTask.getStatus().getValue());
        task.setLimitDate(reqTask.getLimitDate());
        task.setCreatedDate(reqTask.getCreatedDate());
        task.setUpdatedDate(reqTask.getUpdatedDate());
        return task;
    }
}
