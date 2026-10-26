package com.s.looka.core.network.impl

import android.util.Log
import com.s.looka.core.common.DataError
import com.s.looka.core.common.result.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.SerializationException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

private const val TAG = "Network"
private const val INFO = "saveNetworkCall"

@Suppress("UNCHECKED_CAST")
internal suspend fun <T> saveNetworkCall(
    typeInfo: TypeInfo,
    action: suspend () -> HttpResponse,
): Result<T, DataError.Network> {
    return try {
        Log.i(TAG, "$INFO: Making request")
        val response = action()
        Log.i(TAG, "$INFO: Get response")

        // (200 .. 299)
        when (response.status.value) {
            in 200..299 -> {
                Log.i(TAG, "$INFO: Success response")

                // checking for empty JSON response
                if (typeInfo.type == Unit::class) {
                    return Result.success(Unit as T)
                }

                return response.body(typeInfo)
            }

            401 -> {
                Log.i(TAG, "$INFO: Unauthorized response")
                return Result.failure(DataError.Network.UNAUTHORIZED)
            }

            403 -> {
                Log.i(TAG, "$INFO: Forbidden response")
                return Result.failure(DataError.Network.FORBIDDEN)
            }

            404 -> {
                Log.i(TAG, "$INFO: NotFound response")
                return Result.failure(DataError.Network.NOT_FOUND)
            }

            408 -> {
                Log.i(TAG, "$INFO: Timeout response")
                return Result.failure(DataError.Network.REQUEST_TIMEOUT)
            }

            in 500..599 -> {
                Log.i(TAG, "$INFO: Server error")
                return Result.failure(DataError.Network.SERVER_ERROR)
            }

            else -> {
                Log.i(TAG, "$INFO: Unknown error")
                return Result.failure(DataError.Network.UNKNOWN)
            }
        }
    } catch (e: UnknownHostException) {
        Log.e(TAG, "$INFO: Catch exception when do saveNetworkCall function", e)
        Result.failure(DataError.Network.NO_INTERNET)
    } catch (e: SocketTimeoutException) {
        Log.e(TAG, "$INFO: Catch exception when do saveNetworkCall function", e)
        Result.failure(DataError.Network.REQUEST_TIMEOUT)
    } catch (e: SerializationException) {
        Log.e(TAG, "$INFO: Catch exception when do saveNetworkCall function", e)
        Result.failure(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        Log.e(TAG, "$INFO Catch exception when do saveNetworkCall function", e)
        Result.failure(DataError.Network.UNKNOWN)
    }
}