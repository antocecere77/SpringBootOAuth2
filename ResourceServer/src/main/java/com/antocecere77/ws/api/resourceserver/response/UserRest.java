package com.antocecere77.ws.api.resourceserver.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {

    private String userFirstName;
    private String userId;
    private String userLastName;
}
