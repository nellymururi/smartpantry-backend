package com.smartpantry.backend.common

import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Getter
import java.time.Instant

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
class ApiResponse<T> private constructor(
    private val success: Boolean,
    private val message: String?,
    private val data: T?,
    private val error: ApiError?
) {
    private val timestamp: Instant?

    init {
        this.timestamp = Instant.now()
    }

    companion object {
        fun <T> success(data: T?): ApiResponse<T?> {
            return ApiResponse<T?>(true, null, data, null)
        }

        fun <T> success(message: String?, data: T?): ApiResponse<T?> {
            return ApiResponse<T?>(true, message, data, null)
        }

        fun <T> error(message: String?, code: String?): ApiResponse<T?> {
            return ApiResponse<T?>(false, null, null, ApiError(code, message))
        }

        fun <T> error(message: String?, code: String?, details: Any?): ApiResponse<T?> {
            return ApiResponse<T?>(false, null, null, ApiError(code, message, details))
        }
    }
}