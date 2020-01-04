package com.alphilippov.studyingmap.utils

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}