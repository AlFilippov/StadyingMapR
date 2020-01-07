package com.alphilippov.studyingmapnew.utils

import androidx.multidex.MultiDexApplication



import com.alphilippov.studyingmapnew.data.Repository

class MainApplication : MultiDexApplication(), Dependencies.RepositoryProvider,
    Dependencies.SchedulersProvider {

    companion object {

        const val TAG = "MainApplication"

    }

    private lateinit var repository: Repository
    private lateinit var schedulers: Schedulers

    override fun onCreate() {
        super.onCreate()
        makeDependencies()
    }

    private fun makeDependencies(){
        schedulers = DependenciesFactory.createSchedulers()
        repository = DependenciesFactory.createRepository(this)
    }

    override fun getRepository() = repository

    override fun getShedulers() = schedulers
}