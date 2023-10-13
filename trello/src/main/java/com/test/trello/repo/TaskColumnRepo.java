package com.test.trello.repo;

import com.test.trello.enums.State;
import com.test.trello.model.TaskColumn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskColumnRepo extends JpaRepository<TaskColumn, Long> {
    Optional<TaskColumn> findByColumnName(String name);

    Optional<List<TaskColumn>> findByColumnState(State state);
}
