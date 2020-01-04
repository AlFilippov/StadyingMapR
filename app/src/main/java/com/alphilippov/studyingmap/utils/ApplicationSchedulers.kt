


import app.bet.livescores.football.Schedulers
import com.alphilippov.studyingmap.utils.Schedulers
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class ApplicationSchedulers : Schedulers {

    override fun io(): Scheduler = io.reactivex.schedulers.Schedulers.io()

    override fun computation(): Scheduler = io.reactivex.schedulers.Schedulers.computation()

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}