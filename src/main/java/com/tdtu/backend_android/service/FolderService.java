package com.tdtu.backend_android.service;

import com.tdtu.backend_android.model.Folder;
import com.tdtu.backend_android.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService implements CRUDService<Folder> {
    @Autowired
    FolderRepository folderRepository;
    @Override
    public List<Folder> findAll() {
        return folderRepository.findAll();
    }

    @Override
    public Folder findById(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    @Override
    public Folder save(Folder entity) {
        return folderRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        folderRepository.deleteById(id);
    }
}
