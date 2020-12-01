package uk.ac.tees.w9312536.bukolafatunde.utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import uk.ac.tees.w9312536.bukolafatunde.BuildConfig;

public class RetrofitClient {
    /** Static variable for Retrofit */
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            // Add OkHttp interceptor which logs HTTP request and response data only when the debug mode is true.
            // Reference: @see "https://stackoverflow.com/questions/32514410/logging-with-retrofit-2"
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG_MODE) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(interceptor);
            }

            // Create a Retrofit instance using the builder
            sRetrofit = new Retrofit.Builder()
                    // Set the API base URL
                    .baseUrl(Constants.I_TUNES_BASE_URL)
                    .client(okHttpClientBuilder.build())
                    // Use custom ConverterFactory where you delegate to either GsonConverterFactory
                    // or SimpleXmlConverterFactory
                    // Reference: @see "https://stackoverflow.com/questions/40824122/android-retrofit-2-multiple-converters-gson-simplexml-error"
                    // @see "https://speakerdeck.com/jakewharton/making-retrofit-work-for-you-ohio-devfest-2016?slide=86"
                    .addConverterFactory(new XmlOrJsonConverterFactory())
                    .build();
        }
        return sRetrofit;
    }
}
