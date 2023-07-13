package com.tanwei.spring.security.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tanwei
 * @date 2023-07-04 16:09
 **/
@Data
@Builder
public class AuthenticationResponse {

    private String token;
}
