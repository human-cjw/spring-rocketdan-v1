package com.metacoding.springrocketdanv1.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(Integer id, Integer sessionUserId) {
        return userRepository.findById(id);
    }
}