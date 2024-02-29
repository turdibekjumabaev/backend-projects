package org.backend.medium.security;

import org.backend.medium.entity.User;
import org.backend.medium.exeption.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityServices {
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username1 = null;
        User principal = null;

        if (authentication != null && authentication.isAuthenticated()) {
            principal = (User) authentication.getPrincipal();
        }

        if (principal == null) {
            throw new UserNotFoundException("Can not get logged user info from SecurityContextHolder!");
        }

        return principal;
    }
}