import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

private val uri = URI("http://192.168.1.28:9123/elgato/lights")

fun sendRequest(body: String): Int {
    val getRequest = HttpRequest.newBuilder()
        .PUT(HttpRequest.BodyPublishers.ofString(body))
        .uri(uri)
        .build()

    val client = HttpClient.newHttpClient();

    val response: HttpResponse<String>
    try {
        response = client.send(getRequest, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() == 200) {
            return 0
        }
        return response.statusCode()
    } catch (e: Exception) {
        return 1
    }
}