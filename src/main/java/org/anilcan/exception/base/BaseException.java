package org.anilcan.exception.base;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.anilcan.utility.ExceptionMessage;

@Getter
@RequiredArgsConstructor
public abstract class BaseException extends RuntimeException {

    protected final ExceptionMessage exceptionMessage;

}
