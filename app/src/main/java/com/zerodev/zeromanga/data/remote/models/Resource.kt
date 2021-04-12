package com.zerodev.zeromanga.data.remote.models

data class Resource<out T>(val status : Status, val data : T?, val message : String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data : T) : Resource<T> {
            return Resource(status = Status.SUCCESS,data = data, message = null)
        }

        fun<T> error(message: String?,data : T? = null): Resource<T> {
            return Resource(Status.ERROR,data = data,message = message)
        }

        fun<T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING,data,null)
        }
    }
}