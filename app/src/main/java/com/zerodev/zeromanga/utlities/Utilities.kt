package com.zerodev.zeromanga.utlities

fun GetImagenURL(url : String) : String{

    return ""
}

fun AdapterString(url : String ) : String {
    if (url.endsWith("cascade")){
       val urlNew = url.replace("/cascade","/paginated")
        return urlNew
    }
    else if (url.endsWith("paginated")){
        return url
    }
    else if(!url.endsWith("cascade")){
        val newUrl= "$url/paginated"
        return newUrl
    }
    else if (!url.endsWith("paginated")){
        val newUrl = "$url/paginated"
        return newUrl
    }
    return url
}