package id.goldan.technobittest.Model.RestClient.Service;

import id.goldan.technobittest.Model.Response;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ranug on 08/01/2017.
 */

public interface DataService {

    @GET("XMLWebServiceSupraStructure/ApiRequest.aspx")
    Call<Response> getData();
}
