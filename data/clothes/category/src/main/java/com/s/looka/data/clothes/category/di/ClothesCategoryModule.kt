package com.s.looka.data.clothes.category.di

import com.s.looka.data.clothes.category.repository.CategoryRepositoryImpl
import com.s.looka.domain.clothes.category.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClothesCategoryModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}