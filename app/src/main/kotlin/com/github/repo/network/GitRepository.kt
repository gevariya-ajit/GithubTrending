package com.github.repo.network

import com.github.repo.model.Repository
import com.github.repo.model.RepositoryResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GitRepository(private val service: GitService) {

    fun getRepositories(order: Order, language: Language, pageSize: Int, page: Int): Observable<List<Repository>> {
        return Observable.create { emitter ->
            service
                    .getForums(order.name, "language:${language.name}", pageSize, page)
                    .enqueue(object : Callback<RepositoryResponse> {
                        override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                            emitter.onError(t)
                        }

                        override fun onResponse(call: Call<RepositoryResponse>, response: Response<RepositoryResponse>) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    emitter.onNext(it.items)
                                } ?: run {
                                    emitter.onError(Exception("Error! Null body response from server"))
                                }
                            } else {
                                emitter.onError(Exception("Error! ${response.code()} from server"))
                            }
                        }

                    })
        }
    }


    // Values base on API
    enum class Order(value: String) {
        ASC("asc"),
        DESC("desc")
    }

    enum class Language(value: String) {
        KOTLIN("kotlin"),
        JAVA("java")
    }

}
