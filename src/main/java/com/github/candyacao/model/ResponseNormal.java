package com.github.candyacao.model;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ResponseNormal {
    private boolean success;
    @NonNull
    private String msg;
}
