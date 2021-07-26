package com.vds.oauthx.payload.general;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeneralResponse<T> {
    private Integer status;
    private T data;
}
