package com.jefiro.rastreamento.android.Controller;

import com.jefiro.rastreamento.android.Model.DTO.AuthDTO;
import com.jefiro.rastreamento.android.Model.DTO.LoginResponseDTO;
import com.jefiro.rastreamento.android.Model.DTO.UserCreated;
import com.jefiro.rastreamento.android.Model.DTO.UserSumaryDTO;
import com.jefiro.rastreamento.android.Model.DeviceModel;
import com.jefiro.rastreamento.android.Model.TrackerModel;
import com.jefiro.rastreamento.android.Model.UserModel;
import com.jefiro.rastreamento.android.Repository.UserRepository;
import com.jefiro.rastreamento.android.Service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth/v1")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository repository;
    @Autowired
    TokenService tokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody AuthDTO date) {

        var userName = new UsernamePasswordAuthenticationToken(date.email(), date.password());
        var auth = this.authenticationManager.authenticate(userName);

        var userDetails = (UserModel) auth.getPrincipal();

        var token = tokenService.genereteToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(token, new UserSumaryDTO(userDetails)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> singUp(@RequestBody UserCreated date) {
        if (this.repository.findByEmail(date.email()).isPresent()) {
            throw new DataIntegrityViolationException("Conflict: Duplicated tracker ID or other data integrity issue.");
        }

        // Cria o usuário
        var user = new UserModel(date);
        var tracker = new TrackerModel(date.device().trackerID());
        var device = new DeviceModel(date.device());

        device.setUserModel(user);
        device.setTracker(tracker);

        tracker.setDeviceModel(device); // <- se relacionamento bidirecional com tracker

        user.setDevices(List.of(device));

        var response = repository.save(user);

        return ResponseEntity.ok(new UserSumaryDTO(response));
    }

}
