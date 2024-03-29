package com.alphilippov.studyingmapnew.utils

import io.reactivex.Scheduler

interface Schedulers {
    fun io(): Scheduler

    fun computation(): Scheduler

    fun mainThread(): Scheduler
}