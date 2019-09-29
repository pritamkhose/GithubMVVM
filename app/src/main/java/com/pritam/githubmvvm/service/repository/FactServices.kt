package com.pritam.githubmvvm.service.repository

import com.pritam.githubmvvm.service.model.FactResponse
import com.pritam.githubmvvm.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface FactServices {
    @GET(Constants.FACT_URL)
    fun getFactList(): Call<FactResponse>


}