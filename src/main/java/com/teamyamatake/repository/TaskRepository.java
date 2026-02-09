package com.teamyamatake.repository;

import com.teamyamatake.repository.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task> {
    public List<Task> findTop1000ByOrderByLimitDateAsc();
}
