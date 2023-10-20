package com.tdtu.backend_android.service;

import com.tdtu.backend_android.model.Topic;
import com.tdtu.backend_android.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService implements CRUDService<Topic>{
    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    @Override
    public Topic save(Topic entity) {
        return topicRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }
}
