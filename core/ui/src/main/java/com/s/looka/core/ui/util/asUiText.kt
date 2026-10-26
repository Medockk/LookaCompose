package com.s.looka.core.ui.util

import com.s.looka.core.common.DataError
import com.s.looka.core.ui.R

fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Local.DISK_FULL -> UiText.Resource(R.string.error_local_dick_full)
        DataError.Local.NOT_FOUND -> UiText.Resource(R.string.error_local_not_found)
        DataError.Local.UNKNOWN -> UiText.Resource(R.string.error_unknown)
        DataError.Network.NO_INTERNET -> UiText.Resource(R.string.error_network_no_internet)
        DataError.Network.REQUEST_TIMEOUT -> UiText.Resource(R.string.error_network_request_timeout)
        DataError.Network.UNAUTHORIZED -> UiText.Resource(R.string.error_network_unauthorized)
        DataError.Network.FORBIDDEN -> UiText.Resource(R.string.error_network_forbidden)
        DataError.Network.NOT_FOUND -> UiText.Resource(R.string.error_network_not_found)
        DataError.Network.SERVER_ERROR -> UiText.Resource(R.string.error_network_server_error)
        DataError.Network.SERIALIZATION, DataError.Network.UNKNOWN -> UiText.Resource(R.string.error_unknown)
    }
}