package com.verycool.storeapi.di

import com.verycool.storeapi.data.repository.ClothesRepo
import com.verycool.storeapi.data.repository.ClothesRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun providesRepository(
        repoImpl : ClothesRepoImpl
    ) : ClothesRepo

}