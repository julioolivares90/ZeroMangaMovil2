package com.zerodev.zeromanga.data.remote.models

data class Response(val statusCode :Int
                    ,val data : MutableList<Manga>)