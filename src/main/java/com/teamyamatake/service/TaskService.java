package com.teamyamatake.service;

import com.teamyamatake.common.enums.TaskStatus;
import com.teamyamatake.controller.form.FilterForm;
import com.teamyamatake.controller.form.TaskForm;
import com.teamyamatake.repository.TaskRepository;
import com.teamyamatake.repository.entity.Task;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.teamyamatake.repository.specification.TaskSpecification.*;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<TaskForm> findAllTask() {
        List<Task> results = taskRepository.findTop1000ByOrderByLimitDateAsc();
        List<TaskForm> tasks = setTaskForm(results);
        return tasks;
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public List<TaskForm> findByFilter(FilterForm filterForm) {
        LocalDate since = filterForm.getSince() == null ? LocalDate.of(2020, 1, 1) : filterForm.getSince();
        LocalDate until = filterForm.getUntil() == null ? LocalDate.of(2100, 12, 31) : filterForm.getUntil();

        LocalDateTime sinceDateTime = since.atTime(0, 0, 0);
        LocalDateTime untilDateTime = until.atTime(23, 59, 59);

        Integer status = filterForm.getStatus() == null ? null : filterForm.getStatus().getValue();

        String content = filterForm.getContent();

        List<Task> results = taskRepository.findBy(
                limitBetween(sinceDateTime, untilDateTime)
                        .and(statusIs(status))
                       .and(contentIs(content)),
                q -> q
                .limit(1000)
                .sortBy(oderByLimitDate(true))
                .all());
        return setTaskForm(results);
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
     * 編集画面表示
     */
    public TaskForm editTask(Integer id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return null;
        }
        List<Task> results = new ArrayList<>();
        results.add(task);
        List<TaskForm> reports = setTaskForm(results);
        return reports.get(0);
    }


    /*
     *　ステータス更新
     */
    @Transactional
    public void updateStatus(Integer id, Integer status) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
        task.setStatus(status);
        taskRepository.save(task);
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
