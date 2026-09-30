package com.s.looka.features.feature_homepage.presentation

import androidx.lifecycle.ViewModel
import com.s.looka.core.common.viewmodel.BaseViewModelEvent
import com.s.looka.core.common.viewmodel.BaseViewModelHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): BaseViewModelHolder<HomeState, BaseViewModelEvent>() {

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    override val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())

    fun onSearch(s: String) = _search.update { s }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCategoryClick -> {
                val currentState = _state.value
                val categoryId = action.categoryId

                if (currentState.selectedCategoryIds.contains(categoryId)) {
                    _state.update { it.copy(
                        selectedCategoryIds = it.selectedCategoryIds - categoryId
                    ) }
                } else {
                    _state.update { it.copy(
                        selectedCategoryIds = it.selectedCategoryIds + categoryId
                    ) }
                }
            }
        }
    }
}