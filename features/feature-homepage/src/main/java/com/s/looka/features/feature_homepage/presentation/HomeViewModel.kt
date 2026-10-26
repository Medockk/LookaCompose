package com.s.looka.features.feature_homepage.presentation

import androidx.lifecycle.viewModelScope
import com.s.looka.core.common.result.onSuccess
import com.s.looka.core.common.viewmodel.BaseViewModel
import com.s.looka.core.common.viewmodel.CommonUiEvent
import com.s.looka.domain.clothes.category.repository.CategoryRepository
import com.s.looka.domain.clothes.product.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clothesRepository: CategoryRepository,
    private val productRepository: ProductRepository
): BaseViewModel<HomeState, CommonUiEvent>(HomeState()) {

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    init {
        observeCategories()
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            delay(Random.nextDouble(0.5, 5.4).seconds) // simulate real network call
            productRepository.fetchProducts().onSuccess { products ->
                updateState { it.copy(
                    products = products
                ) }
            }
        }
    }

    fun onSearch(s: String) = _search.update { s }
    fun observeCategories() {
        viewModelScope.launch {
            clothesRepository.observeAllCategories()
                .collect { categoryResult ->
                    categoryResult.onSuccess { categories ->
                        updateState {
                            it.copy(categories = categories)
                        }
                    }
                }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCategoryClick -> {
                val categoryId = action.categoryId

                if (currentState.selectedCategoryIds.contains(categoryId)) {
                    updateState { it.copy(
                        selectedCategoryIds = it.selectedCategoryIds - categoryId
                    ) }
                } else {
                    updateState { it.copy(
                        selectedCategoryIds = it.selectedCategoryIds + categoryId
                    ) }
                }
            }
        }
    }
}