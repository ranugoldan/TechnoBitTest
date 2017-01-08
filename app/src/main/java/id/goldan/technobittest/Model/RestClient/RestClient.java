package id.goldan.technobittest.Model.RestClient;

import id.goldan.technobittest.Model.RestClient.Service.DataService;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ranug on 08/01/2017.
 */

public class RestClient {
    public static Retrofit retrofit;
    public static DataService dataService;

    public static void initialize(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://windev1.buquplatform.net/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        dataService = retrofit.create(DataService.class);
    }
}
