package com.filesharing.account.service;

import com.filesharing.account.model.File;

import java.util.List;

public interface FileService {
    public List<File> listFilesOfUser(int id);
    public void addFile(File p);
    public void updateFile(File p);
    public List<File> listFiles();
    public File getFileById(int id);
    public File getFileByPath(String id);
    public void removeFile(int id,int user_req_id);
    public void save(File file);
}
