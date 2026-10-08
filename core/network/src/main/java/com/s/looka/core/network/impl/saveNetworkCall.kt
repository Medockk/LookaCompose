package com.s.looka.core.network.impl

import android.util.Log
import com.s.looka.core.common.result.Result
import com.s.looka.core.network.api.NetworkError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.util.reflect.TypeInfo

private const val TAG = "Network"
private const val INFO = "saveNetworkCall"

@Suppress("UNCHECKED_CAST")
internal suspend fun <T> saveNetworkCall(
    typeInfo: TypeInfo,
    action: suspend () -> HttpResponse,
): Result<T> {
    return try {
        Log.i(TAG, "$INFO: Making request")
        val response = action()
        Log.i(TAG, "$INFO: Get response")

        // (200 .. 299)
        if (response.status.isSuccess()) {
            Log.i(TAG, "$INFO: Success response")

            // checking for empty JSON response
            if (typeInfo.type == Unit::class) {
                return Result.success(Unit as T)
            }

            if (typeInfo.type == List::class) {
                return Result.success(emptyList<T>() as T)
            }

            if (response.status == HttpStatusCode.NoContent) {
                return Result.success(null as T)
            }

        }

        if (response.status == HttpStatusCode.Forbidden) {
            Log.i(TAG, "$INFO: Forbidden response")
            return Result.failure(NetworkError.Forbidden)
        }
        if (response.status == HttpStatusCode.RequestTimeout) {
            Log.i(TAG, "$INFO: RequestTimeout response")
            return Result.failure(NetworkError.RequestTimeout)
        }

        val body: T = response.body(typeInfo)
        return Result.success(body)

    } catch (e: Exception) {
        Log.e(TAG, "$INFO Catch exception when do saveNetworkCall function", e)
        Result.failure(NetworkError.UnknowError)
    }
}