package com.tanwei.spring.security.security.dynamic;

import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;

/**
 * @author tanwei
 * @date 2023-07-12 9:59
 **/
@Component
public class AccessAuthorizationFilter extends AuthorizationFilter {


    /**
     * Creates an instance.
     *
     * @param authorizationManager the {@link AuthorizationManager} to use
     */
    public AccessAuthorizationFilter(AccessAuthorizationManagerAdapter authorizationManager) {
        super(authorizationManager);
    }
}
