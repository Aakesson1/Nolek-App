import com.example.nolekapp.Database.Data.LeakTestData
import com.example.nolekapp.Database.Data.TestResultat
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.Call

interface LeakTestApiService {

    @GET("api/MongoDB")
    fun getAllLeakTests(): Call<List<LeakTestData>>
    @POST("api/MongoDB")
    fun addLeakTest(@Body newTest: TestResultat): Call<TestResultat>


}
