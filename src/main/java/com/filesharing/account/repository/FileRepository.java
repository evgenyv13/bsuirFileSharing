package com.filesharing.account.repository;

import com.filesharing.account.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
