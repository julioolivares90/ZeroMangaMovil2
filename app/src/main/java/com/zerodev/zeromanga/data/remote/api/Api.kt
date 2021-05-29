package com.zerodev.zeromanga.data.remote.api

import com.zerodev.zeromanga.data.remote.models.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("Mangas/Mangas")
    suspend fun  GetData() : Response<MangaData>

    @GET("Mangas/Mangas-populares/{pageNumber}")
    suspend fun getAllMangasPopulares(@Path("pageNumber") pgeNumber : Int) : com.zerodev.zeromanga.data.remote.models.Response

    @GET("Mangas/Mangas-seinen")
    suspend fun getAllMangasSeinen() : com.zerodev.zeromanga.data.remote.models.Response

    @GET("Mangas/Manga-info")
    suspend fun getInfoManga(@Query("urlManga") mangaUrl : String) : MangaResponse

    @GET("Mangas/Buscar-manga")
    suspend fun getMangasLibrary(
        @Query("title") title: String?,
        @Query("_page") page : String?,
        @Query("orderItem") orderItem : String?,
        @Query("orderDir") orderDir: String?,
        @Query("filter_by") filter_by : String?,
        @Query("Type") Type : String?,
        @Query("demography") demography : String?,
        @Query("status") status : String?,
        @Query("translation_status") translation_status : String?,
        @Query("webcomic") webcomic : String?,
        @Query("yonkoma") yonkoma : String?,
        @Query("amateur") amateur : String?,
        @Query("erotic") erotic : String?) : com.zerodev.zeromanga.data.remote.models.Response


    @GET("manga/listas")
    suspend fun getListasMangas() : ResponseLista

    @GET("get-manga")
    suspend fun getPaginasOfManga(@Query("urlPage") lectorTMO : String) : ResultListPaginasManga

    @GET("Mangas/Manga-paginas")
    suspend fun getPaginasFromChapter(@Query("urlRefer") urlRefer: String
                                      ,@Query("urlCapitulo") urlChapter: String) : Chapters

}