package com.s.looka.core.navigation

/**
 * @param withLocalNavigationBarProvider if `true`, then control of navigationBarVisibility transferred to com.s.looka.core.ui.util.LocalNavigationBarVisibility
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ShowNavigationBar(val withLocalNavigationBarProvider: Boolean = true)
