package com.jefiro.rastreamento.android.Repository;

import com.jefiro.rastreamento.android.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,String> {
    Optional<UserModel> findByEmail(String email);

}
