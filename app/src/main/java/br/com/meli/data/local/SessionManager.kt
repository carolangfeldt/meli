import android.content.Context
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SessionManager(private val context: Context) {
    private val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    private val _logoutFlow = MutableSharedFlow<Unit>(replay = 0)
    val logoutFlow: SharedFlow<Unit> = _logoutFlow

    private val sessionScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun saveToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }

    fun getToken(): String? = prefs.getString("access_token", null)

    fun clearSession() {
        prefs.edit().clear().apply()
        sessionScope.launch {
            _logoutFlow.emit(Unit)
        }
    }
}