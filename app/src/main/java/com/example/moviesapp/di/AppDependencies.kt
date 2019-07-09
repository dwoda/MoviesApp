package com.example.moviesapp.di

import com.example.moviesapp.MoviesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ActivitiesModule::class, ApiModule::class, AndroidInjectionModule::class]
)
interface AppDependencies : AndroidInjector<MoviesApp> {
    override fun inject(application: MoviesApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MoviesApp): Builder

        fun build(): AppDependencies
    }
}