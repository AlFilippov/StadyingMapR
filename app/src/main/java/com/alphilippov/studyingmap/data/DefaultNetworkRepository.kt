
import app.bet.livescores.football.Schedulers
import app.bet.livescores.football.data.model.FootballHockey
import com.alphilippov.studyingmap.data.DataApi
import com.alphilippov.studyingmap.data.NetworkRepository
import com.alphilippov.studyingmap.data.model.FootballHockey
import com.alphilippov.studyingmap.utils.Schedulers
import io.reactivex.Single

class DefaultNetworkRepository(
        private val api: DataApi,
        private val schedulers: Schedulers
) : NetworkRepository {
    override fun getBets(
        apiKey: String,
        sport: String,
        region: String,
        mkt: String
    ): Single<FootballHockey> =
        api.getBets(apiKey, sport, region, mkt)




}