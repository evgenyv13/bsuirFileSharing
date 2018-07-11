package com.filesharing.account.service;

import com.filesharing.account.DAO.FileDAOImpl;
import com.filesharing.account.model.User;
import com.filesharing.account.repository.RoleRepository;
import com.filesharing.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private FileDAOImpl fileDAO;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void setFileDAO(FileDAOImpl fileDAO) {
        this.fileDAO = fileDAO;
    }

    public FileDAOImpl getFileDAO() {
        return fileDAO;
    }
}