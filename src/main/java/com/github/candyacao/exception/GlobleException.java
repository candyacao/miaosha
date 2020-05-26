package com.github.candyacao.exception;

import com.github.candyacao.common.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobleException extends RuntimeException {
    private ResultStatus status;
}
