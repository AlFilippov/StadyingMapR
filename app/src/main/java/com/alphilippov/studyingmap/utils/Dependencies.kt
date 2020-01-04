package com.alphilippov.studyingmap.utils

import com.alphilippov.studyingmap.utils.Dependencies.Companion.repository
import com.alphilippov.studyingmap.utils.Dependencies.Companion.schedulers
import android.app.Application
import android.content.Context
import com.alphilippov.studyingmap.data.Repository
import java.lang.IllegalStateException

data class Dependencies(
        val repository: Repository,
        val schedulers: Schedulers
) {

    interface RepositoryProvider {
        fun getRepository(): Repository
    }

    interface SchedulersProvider {
        fun getShedulers(): Schedulers
    }

    companion object {

        fun schedulers(context: Context): Schedulers {
            val application = context.applicationContext as Application
            if (application is SchedulersProvider) {
                return (application as SchedulersProvider).getShedulers()
            }
            throw IllegalStateException("Application class should implement SchedulersProvider interface")
        }

        fun repository(context: Context): Repository {
            val application = context.applicationContext as Application
            if (application is RepositoryProvider) {
                return (application as RepositoryProvider).getRepository()
            }
            throw IllegalStateException("Application class should implement RepositoryProvider interface")
        }
    }
}

fun Context.dependencies() = Dependencies(
    repository(this),
    schedulers(this)
)