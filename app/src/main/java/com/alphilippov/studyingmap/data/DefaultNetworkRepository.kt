import com.alphilippov.studyingmap.data.DataApi
import com.alphilippov.studyingmap.data.NetworkRepository
import com.alphilippov.studyingmap.data.model.CoursesData
import com.alphilippov.studyingmap.utils.Schedulers
import io.reactivex.Single

class DefaultNetworkRepository(
        private val api: DataApi,
        private val schedulers: Schedulers
) : NetworkRepository {
    override fun getCourses(page: Int, page_size: Int, search: String, price: String, aff: Boolean, lang: String, level: String, order: String, ratings: Int): Single<CoursesData>
            = api.getCourses(page, page_size, search, price, aff, lang, level, order, ratings)


}