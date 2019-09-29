package com.pritam.githubmvvm.service.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pritam.githubmvvm.database.DatabaseCache
import com.pritam.githubmvvm.extensions.customPrefs
import com.pritam.githubmvvm.extensions.get
import com.pritam.githubmvvm.extensions.set
import com.pritam.githubmvvm.service.model.FactResponse
import com.pritam.githubmvvm.service.model.UserSerachResponse
import com.pritam.githubmvvm.utils.ConnectivityUtils
import com.pritam.githubmvvm.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

open class GitRepository {
    private var apiInterfaceServices: ApiInterfaceServices? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiInterfaceServices = retrofit.create(ApiInterfaceServices::class.java)
    }

    companion object {
        private var gitRepository: GitRepository? = null
        @Synchronized
        @JvmStatic
        fun getInstance(): GitRepository {
            if (gitRepository == null) {
                gitRepository = GitRepository()
            }
            return gitRepository!!
        }
    }


    /**
     * If network is available then load updated data from server and saved it in SQLite database.
     * If network is not available then will load data from SQLite database
     *
     * @param context Context
     * @param databaseCache DatabaseCache
     *
     * @return FactResponse
     */
    fun getFactList(context: Context, databaseCache: DatabaseCache): LiveData<FactResponse> {
        val data = MutableLiveData<FactResponse>()
        val prefs = customPrefs(context, Constants.APP_SHARED_PREFS)

        if (databaseCache.getAllFacts().isEmpty()) {
            // no data in database, load from server
            data.value = fetchFactsFromServer(data, databaseCache, context)

        } else {
            if (ConnectivityUtils.isNetworkAvailable(context)) {
                // data is present in db but network is present so will load updated data
                data.value = fetchFactsFromServer(data, databaseCache, context)
            } else {
                // data is present in db but nrtwork is not present so will data from db
                Log.d(Constants.APP_TAG, "data response from database: " + data)
                data.value = FactResponse(prefs[Constants.PREFS_TOOLBAR]!!, databaseCache.getAllFacts())
            }

        }
        return data
    }

    /**
     * Load data from server and saved it in database, delete if any available in db
     *
     * @param data MutableLiveData<FactResponse>
     * @param databaseCache DatabaseCache
     * @param context Context
     *
     * @return FactResponse
     */
    private fun fetchFactsFromServer(data: MutableLiveData<FactResponse>, databaseCache: DatabaseCache, context: Context): FactResponse? {
        val prefs = customPrefs(context, Constants.APP_SHARED_PREFS)
        apiInterfaceServices?.getFactList()?.enqueue(object : Callback<FactResponse> {
            override fun onResponse(call: Call<FactResponse>, response: Response<FactResponse>) {
                Log.d(Constants.APP_TAG, "data response from server: " + response.body())
                data.value = response.body()

                // delete all facts from database and insert updated one
                databaseCache.deleteAllFacts()
                databaseCache.insert(response.body()?.rows?.toMutableList()!!)

                // add Toolbar in Preference
                prefs[Constants.PREFS_TOOLBAR] = response.body()?.title

            }

            override fun onFailure(call: Call<FactResponse>, t: Throwable) {
                Log.d(Constants.APP_TAG, "Error While response : " + t.printStackTrace())
                data.value = null
            }
        })

        return data.value
    }


    fun getUserSerachList(context: Context, databaseCache: DatabaseCache): LiveData<UserSerachResponse> {
        val data = MutableLiveData<UserSerachResponse>()
        val prefs = customPrefs(context, Constants.APP_SHARED_PREFS)

//        if (databaseCache.getUserSerachList().isEmpty()) {
//            // no data in database, load from server
//            data.value = fetchUserSerachFromServer(data, databaseCache, context)
//
//        } else {
            if (ConnectivityUtils.isNetworkAvailable(context)) {
                // data is present in db but network is present so will load updated data
                data.value = fetchUserSerachFromServer(data, databaseCache, context)
            } else {
                // data is present in db but nrtwork is not present so will data from db
//                Log.d(Constants.APP_TAG, "data response from database: " + data)
//                data.value = UserSerachResponse(prefs[Constants.PREFS_TOOLBAR]!!, databaseCache.getAllUserSerach())
//            }

        }
        return data
    }

    private fun fetchUserSerachFromServer(data: MutableLiveData<UserSerachResponse>, databaseCache: DatabaseCache, context: Context): UserSerachResponse? {
        val prefs = customPrefs(context, Constants.APP_SHARED_PREFS)
        apiInterfaceServices?.getUserSerachList("pritam", 1)?.enqueue(object : Callback<UserSerachResponse> {
            override fun onResponse(call: Call<UserSerachResponse>, response: Response<UserSerachResponse>) {
                Log.d(Constants.APP_TAG, "data response from server: " + response.body())
                data.value = response.body()

                // delete all facts from database and insert updated one
//                databaseCache.deleteAllFacts()
//                databaseCache.insert(response.body()?.items?.toMutableList()!!)

                // add Toolbar in Preference
                prefs[Constants.PREFS_TOOLBAR] = "Pritam"

            }

            override fun onFailure(call: Call<UserSerachResponse>, t: Throwable) {
                Log.d(Constants.APP_TAG, "Error While response : " + t.printStackTrace())
                data.value = null
            }
        })

        return data.value
    }
}