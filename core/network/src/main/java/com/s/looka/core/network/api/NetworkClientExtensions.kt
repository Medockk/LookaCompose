package com.s.looka.core.network.api

import com.s.looka.core.common.result.Result

suspend inline fun <reified T> NetworkClient.get(
    url: String,
    noinline block: (NetworkClient.Builder.() -> Unit)? = null,
): Result<T> = this.request(
    url = url,
    clazz = T::class,
    methodType = NetworkClient.MethodType.GET,
    block = block
)

suspend inline fun <reified T> NetworkClient.post(
    url: String,
    noinline block: (NetworkClient.Builder.() -> Unit)? = null,
): Result<T> = this.request(
    url = url,
    clazz = T::class,
    methodType = NetworkClient.MethodType.POST,
    block = block
)

suspend inline fun <reified T> NetworkClient.put(
    url: String,
    noinline block: (NetworkClient.Builder.() -> Unit)? = null,
): Result<T> = this.request(
    url = url,
    clazz = T::class,
    methodType = NetworkClient.MethodType.PUT,
    block = block
)

suspend inline fun <reified T> NetworkClient.delete(
    url: String,
    noinline block: (NetworkClient.Builder.() -> Unit)? = null,
): Result<T> = this.request(
    url = url,
    clazz = T::class,
    methodType = NetworkClient.MethodType.DELETE,
    block = block
)