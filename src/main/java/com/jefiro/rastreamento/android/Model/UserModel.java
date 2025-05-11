package com.jefiro.rastreamento.android.Model;

import com.jefiro.rastreamento.android.Model.DTO.UserCreated;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "app_user")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String _id;


    private String name;
    @Column(unique = true)
    private String email;
    private String passWord;
    private UserRole role;

    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private List<DeviceModel> devices;


    public UserModel(UserCreated date) {
        this.name = date.name();
        this.email = date.email();
        this.passWord = new BCryptPasswordEncoder().encode(date.password());
        this.role = UserRole.ADMIN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_ADMIN"));
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
