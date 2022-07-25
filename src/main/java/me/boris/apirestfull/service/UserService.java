package me.boris.apirestfull.service;

import me.boris.apirestfull.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Iterable<User> findAll();

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Integer id);

    User save(User user);

    void delete(Integer id);
}
