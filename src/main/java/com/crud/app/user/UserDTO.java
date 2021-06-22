package com.crud.app.user;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class UserDTO {
    private Integer userId;
    private String email;
    private String firstName;
    private String lastName;
}
