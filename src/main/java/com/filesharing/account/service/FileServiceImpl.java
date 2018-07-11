package com.filesharing.account.service;

import com.filesharing.account.DAO.FileDAO;
import com.filesharing.account.model.File;
import com.filesharing.account.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    private FileDAO fileDAO;

    @Override
    @Transactional
    public void addFile(File p) {
        this.fileDAO.addFile(p);
    }

    @Override
    @Transactional
    public void updateFile(File p) {
        this.fileDAO.updateFile(p);
    }

    @Autowired
    private FileRepository fileRepository;

    public void setFileDAO(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }


    @Override
    @Transactional
    public List<File> listFiles() {
        return this.fileDAO.listFiles();
    }

    @Override
    @Transactional
    public List<File> listFilesOfUser(int id) {
        return this.fileDAO.listFilesOfUser(id);
    }

    @Override
    @Transactional
    public File getFileById(int id) {
        return this.fileDAO.getFileById(id);
    }

    @Override
    @Transactional
    public File getFileByPath(String id) {
        return this.fileDAO.getFileByPath(id);
    }


    @Override
    @Transactional
    public void removeFile(int id,int user_req_id) {
        this.fileDAO.removeFile(id,user_req_id);
    }

    public void save(File file) {
        fileRepository.save(file);
    }
}
