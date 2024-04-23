package com.github.cadecode.ubp.common.exception;

import com.github.cadecode.ubp.common.enums.ErrorCode;
import lombok.Getter;

import java.util.Objects;

/**
 * 通用异常类
 *
 * @author Cade Li
 * @date 2022/5/8
 */
@Getter
public class GeneralException extends BaseException {

    /**
     * 错误信息码
     */
    private final ErrorCode errorCode;

    /**
     * 更多错误信息
     */
    private final String moreInfo;

    /**
     * 构造方法
     *
     * @param errorCode 错误信息码
     * @param throwable cause
     * @param moreInfo  更多异常信息
     */
    public GeneralException(ErrorCode errorCode, Throwable throwable, String moreInfo, Object... params) {
        super(geneErrorMessage(errorCode, moreInfo), throwable, params);
        this.errorCode = errorCode;
        this.moreInfo = moreInfo;
    }

    /**
     * 抛出未知异常
     *
     * @param moreInfo 更多异常信息
     * @return GeneralException
     */
    public static GeneralException of(String moreInfo, Object... params) {
        return of(ErrorCode.UNKNOWN, moreInfo, params);
    }

    /**
     * 抛出未知异常
     *
     * @param throwable cause
     * @param moreInfo  更多异常信息
     * @return GeneralException
     */
    public static GeneralException of(Throwable throwable, String moreInfo, Object... params) {
        return of(ErrorCode.UNKNOWN, throwable, moreInfo, params);
    }

    /**
     * 根据 ErrorCode 抛出异常
     *
     * @param errorCode 错误信息码
     * @param moreInfo  更多异常信息
     * @return GeneralException
     */
    public static GeneralException of(ErrorCode errorCode, String moreInfo, Object... params) {
        return of(errorCode, null, moreInfo, params);
    }

    /**
     * 根据 ErrorCode 抛出异常
     *
     * @param errorCode 错误信息码
     * @param throwable cause
     * @return GeneralException
     */
    public static GeneralException of(ErrorCode errorCode, Throwable throwable) {
        return of(errorCode, throwable, null);
    }

    /**
     * 根据 ErrorCode 抛出异常
     *
     * @param errorCode 错误信息码
     * @param throwable cause
     * @param moreInfo  更多异常信息
     * @return GeneralException
     */
    public static GeneralException of(ErrorCode errorCode, Throwable throwable, String moreInfo, Object... params) {
        return new GeneralException(errorCode, throwable, moreInfo, params);
    }

    /**
     * 根据 ErrorCode、moreInfo 构造错误信息
     *
     * @param errorCode 错误信息码
     * @param moreInfo  更多异常信息
     * @return 完整异常信息
     */
    private static String geneErrorMessage(ErrorCode errorCode, String moreInfo) {
        String message = "";
        // 拼接 [错误码:错误信息]
        if (Objects.nonNull(errorCode)) {
            message += "[" + errorCode.getCode() + ":" + errorCode.getMessage() + "]";
        }
        // 拼接更多异常信息
        if (Objects.nonNull(moreInfo)) {
            message += moreInfo;
        }
        return message;
    }
}
