package com.chingon.FoodStorageApp.shared.audit;

import com.chingon.FoodStorageApp.user.CurrentUserService;
import com.chingon.FoodStorageApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
@RequiredArgsConstructor
public class AuditAwareImpl implements AuditorAware<String> {
    /**
     *
     * @return the current auditor
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("system");
        }

        return Optional.ofNullable(authentication.getName());
    }
}
