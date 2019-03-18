package com.india.loginsampleretrofit.retrofit;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Agniva on 04-10-2018.
 */
public interface RestInterface {
    @FormUrlEncoded
    @POST(Constant.BOOK_LOTO)
    Call<ResponseBody> FetchingBookLottoService(@Field("book_numbers") String book_numbers,
                                                     @Field("username") String username,
                                                     @Field("email") String email);

//    @GET(Constant.GET_QUIZ)
//    Call<QuizResponse> getQuizQuestionAnswer(@Query("status") String method);
//
//    @GET(Constant.PATTERN)
//    Call<PatternResponse> getPatterResponse(@Query("method") String method);
//
//    @FormUrlEncoded
//    @POST(Constant.SAVE_PATTERN)
//    Call<ResponseBody> savePatterResponse(@Field("book_pattern") String book_pattern,
//                                          @Field("username") String username,
//                                          @Field("email") String email);
//
//    @FormUrlEncoded
//    @POST(Constant.SAVE_QIUZ_ANSWER)
//    Call<ResponseBody> saveQuizResponse(@Field("question") String book_pattern,
//                                        @Field("option_1") String option_1,
//                                        @Field("option_2") String option_2,
//                                        @Field("option_3") String option_3,
//                                        @Field("option_4") String option_4,
//                                        @Field("answere") String answere,
//                                        @Field("question_no") String question_no,
//                                        @Field("username") String username,
//                                        @Field("email") String email);
//    @GET(Constant.SHOW_ACHIVEMENT)
//    Call<RegisterResponse> getRegisterResponse(@Query("method") String data);


}

