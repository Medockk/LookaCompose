package com.s.looka.core.navigation

class NavigationDestinationRegistry {

    private val destinations = mutableMapOf<String, NavigationDestinationMetadata>()

    fun registry(route: String, metadata: NavigationDestinationMetadata) {
        destinations[route] = metadata
    }

    fun metadataFor(route: String?): NavigationDestinationMetadata? {
        return route?.let(destinations::get)
    }
}