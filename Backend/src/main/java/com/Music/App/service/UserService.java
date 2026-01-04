package com.Music.App.service;

import com.Music.App.model.User;
import com.Music.App.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public Optional<User> getUserByUsername(String username){
        log.info("Kullanıcı adına göre sorgulama yapılıyor: {}", username);
        return userRepo.findByUsername(username);
    }
    public Optional<User> getUserByEmail(String email){
        log.info("Email adresine göre kullanıcı aranıyor: {}", email);
        return userRepo.findByEmail(email);
    }
    public List<User> getPassiveUser(){
        log.info("Hiç çalma listesi bulunmayan pasif kullanıcılar listeleniyor.");
        List<User> passiveUsers= userRepo.findPassiveUser();
        log.info("{} adet pasif kullanıcı bulundu.", passiveUsers.size());
        return passiveUsers;
    }
    public User registerUser(User user){
        return userRepo.save(user);
    }

}
