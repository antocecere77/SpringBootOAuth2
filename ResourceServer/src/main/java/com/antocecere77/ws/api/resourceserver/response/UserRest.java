package com.antocecere77.ws.api.resourceserver.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRest {

    private String userId;
    private String userFirstName;
    private String userLastName;
}
