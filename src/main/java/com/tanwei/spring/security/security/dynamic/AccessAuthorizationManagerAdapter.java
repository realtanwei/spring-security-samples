package com.tanwei.spring.security.security.dynamic;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author tanwei
 * @date 2023-07-11 17:40
 **/
@Component
@RequiredArgsConstructor
public class AccessAuthorizationManagerAdapter implements AuthorizationManager<HttpServletRequest> {

    private final AccessSecurityMetadataSource securityMetadataSource;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest servletRequest) {

        Collection<ConfigAttribute> configAttributes = this.securityMetadataSource.getAttributes(servletRequest);

        if (CollectionUtils.isEmpty(configAttributes)) {
            return null;
        }
        Authentication auth = authentication.get();
        for (ConfigAttribute configAttribute : configAttributes) {
            String needAuthority = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
                    return null;
                }
            }
        }
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }
}
