import br.com.meli.data.model.AuthTokenResponse
import retrofit2.http.*

interface MeliAuthApi {
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun exchangeCode(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String
    ): AuthTokenResponse
}