package com.metacoding.springrocketdanv1.user;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;

    @Transactional
    public void 회원가입(UserRequest.JoinDTO joinDTO) {
        userRepository.save(joinDTO.toEntity());

    }

}