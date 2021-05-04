package io.giodude.americanfootball.Network;

import io.giodude.americanfootball.Model.MatchesModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MatchesApiService {

    String PAST_URL = "https://www.thesportsdb.com/api/v1/json/1/";

    @GET("eventspastleague.php")
    Observable<MatchesModel> getPast(@Query("id")String past);
}
