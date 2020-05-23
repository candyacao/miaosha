package com.github.candyacao.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class ResponseNormal {
    private boolean success;
    @NonNull
    private String msg;
}
