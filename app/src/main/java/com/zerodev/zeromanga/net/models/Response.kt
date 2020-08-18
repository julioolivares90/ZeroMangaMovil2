package com.zerodev.zeromanga.net.models

data class Response(val statusCode :Int
                    ,val data : MutableList<Manga>)