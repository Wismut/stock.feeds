package com.example.stock.feeds.service;

import com.example.stock.feeds.model.User;
import com.example.stock.feeds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public Mono<User> findById(@NonNull Integer id) {
        return repository.findById(id);
    }
}
