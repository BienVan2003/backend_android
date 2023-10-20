package com.tdtu.backend_android.repository;

import com.tdtu.backend_android.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
