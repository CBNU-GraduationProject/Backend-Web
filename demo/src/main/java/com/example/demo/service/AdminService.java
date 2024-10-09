package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(String username, String password) {
        Admin admin = new Admin(username, password); // id를 제외한 생성자 사용
        return adminRepository.save(admin);
    }
}

