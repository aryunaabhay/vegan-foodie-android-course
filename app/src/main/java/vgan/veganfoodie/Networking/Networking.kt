package vgan.veganfoodie.Networking

import android.os.AsyncTask
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL


/**
 * Created by aryuna on 11/11/17.
 */
typealias requestResponse = (json: JsonElement?) -> Unit

enum class HTTPVerb {
    POST, GET, PUT, DELETE, HEAD
}

class Networking {
    companion object {
        fun request(verb: HTTPVerb, urlString: String, completion: requestResponse) {
            var req = ServerRequest()
            req.url = URI.create(urlString).toURL()
            req.completion = completion
            req.execute()
        }
    }
}

class ServerRequest : AsyncTask<Void, Void, JsonElement>() {
    lateinit var url: URL
    var verb: HTTPVerb = HTTPVerb.GET
    lateinit var completion: requestResponse

    override fun doInBackground(vararg params: Void?): JsonElement? {
        try {
            val client = this.url.openConnection() as HttpURLConnection
            client.setRequestMethod(this.verb.toString())
            client.setConnectTimeout(20000)
            client.setReadTimeout(20000)

            client.connect()
            val responseStream = client.inputStream
            val stringBuilder = StringBuilder()
            assert(responseStream != null)
            val bufferedReader = BufferedReader(InputStreamReader(responseStream))

            var line: String? = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            }
            return JsonParser().parse(stringBuilder.toString())

        } catch (e: IOException) {
            e.printStackTrace();
        }
        return null;
    }

    override fun onPostExecute(result: JsonElement?) {
        super.onPostExecute(result);
        this.completion(result)
    }
}