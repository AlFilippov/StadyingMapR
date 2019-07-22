package com.alphilippov.studyingmap.network;

import com.alphilippov.studyingmap.network.dto.UserModelDto;

import retrofit2.Call;
import retrofit2.http.Query;

public interface UdemyApiIn {
    Call<UserModelDto> getResult(int page,
                                 int page_size,
                                 String search,
                                 String price,
                                 boolean aff,
                                 String lang,
                                 String level,
                                 String order,
                                 int ratings);
}
