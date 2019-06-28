package com.alphilippov.studyingmap.network;

import com.alphilippov.studyingmap.network.dto.GetProfessionDTO;
import com.alphilippov.studyingmap.network.dto.ProfessionDataListDTO;
import com.alphilippov.studyingmap.network.dto.UserModelDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    @GET("courses")
    Call<UserModelDto> getResult(@Query("page") int page,
                                 @Query("page_size") int page_size,
                                 @Query("search") String search,
                                 @Query("price") String price,
                                 @Query("is_affiliate_agreed") boolean aff,
                                 @Query("language") String lang,
                                 @Query("instructional_level") String level,
                                 @Query("ordering") String order,
                                 @Query("ratings") int ratings);

    @GET("data/profession")
    Call<ProfessionDataListDTO> getProfessionId(@Query("id") long id);


    @GET("data/profession/info")
    Call<GetProfessionDTO> getAllProfession(@Query("id") long id);
}
