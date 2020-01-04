

import app.bet.livescores.football.data.model.Odds
import com.alphilippov.studyingmap.data.model.Odds
import com.google.gson.annotations.SerializedName

data class BetsSite(
    @field:SerializedName("site_key") val nameSite:String,
    @field:SerializedName("site_nice") val nameRightSite:String,
    @field:SerializedName("odds")val odds: Odds
) {
}