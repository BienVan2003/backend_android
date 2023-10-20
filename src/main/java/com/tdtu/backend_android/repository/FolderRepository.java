package com.tdtu.backend_android.repository;

import com.tdtu.backend_android.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
