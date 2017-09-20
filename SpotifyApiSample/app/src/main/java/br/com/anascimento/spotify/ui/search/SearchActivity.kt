package br.com.anascimento.spotify.ui.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.anascimento.spotify.R
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse

import kotlinx.android.synthetic.main.activity_search.*
import android.content.Intent
import android.util.Log
import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter
import com.afollestad.materialdialogs.MaterialDialog
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import br.com.anascimento.spotify.models.ArtistDetail
import br.com.anascimento.spotify.models.SearchSpotify
import br.com.anascimento.spotify.service.ConstantsService
import br.com.anascimento.spotify.service.SpotifySearchService
import br.com.anascimento.spotify.ui.adapter.ArtistSearchAdapter
import kotlinx.android.synthetic.main.content_search.*
import org.apache.commons.lang3.StringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class SearchActivity : AppCompatActivity() {

    companion object {
        @JvmStatic val TAG_LOG = "AUHT_SPOTIFY"
        @JvmStatic val CLIENT_ID = "630bab7485ae46218c596a237b391c7e"
        @JvmStatic val AUTH_TOKEN_REQUEST_CODE = 1
        @JvmStatic val AUTH_CODE_REQUEST_CODE = 2
    }

    val adapter : ArtistSearchAdapter? = ArtistSearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(false)
        fabSpeedDial.setMenuListener(object : SimpleMenuListenerAdapter() {
            override fun onMenuItemSelected(menuItem: MenuItem?): Boolean {
                fabSpeedMenuItemSelected(menuItem)
                return false
            }
        })

        val recyclerView = listArtists
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        //fab.setOnClickListener { view -> onRequestCode() } //onRequestToken() }
    }

    fun updateListArtists(items: ArrayList<ArtistDetail>?) {
        adapter?.setLista(items)
        adapter?.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem?): Boolean {
        if (menuItem?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(menuItem)
    }

    fun fabSpeedMenuItemSelected(menuItem: MenuItem?) {
        if (menuItem?.itemId == R.id.action_search) {
            showDialgInputArtist()
        }
    }

    private var artistName: String? = null

    fun showDialgInputArtist() {
        MaterialDialog.Builder(this)
                .title(R.string.dlg_input_search_title)
                .content(R.string.dlg_input_search_content)
                .inputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .input("", StringUtils.trimToEmpty(artistName), { dialog, input ->
                    dialog.dismiss()
                    artistName = StringUtils.trimToEmpty(input.toString())
                    sendSearchArtist()
                }).show()
    }

    fun sendSearchArtist() {
        if (StringUtils.isBlank(artistName))
            return
        if (mAccessToken == null) {
            onRequestToken()
        } else {
            val retrofit = Retrofit.Builder()
                    .baseUrl(ConstantsService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val spotifySearchService = retrofit.create(SpotifySearchService::class.java)
            val requestSearchItems = spotifySearchService.searchItems(mAccessToken, artistName, "artist")
            requestSearchItems.enqueue(object : Callback<SearchSpotify> {
                override fun onFailure(call: Call<SearchSpotify>?, t: Throwable?) {
                    Log.d(TAG_LOG, "sendSearchArtist() Erro: " + t?.message)
                }

                override fun onResponse(call: Call<SearchSpotify>?, response: Response<SearchSpotify>?) {
                    if (response?.isSuccessful == false) {
                        Log.d(TAG_LOG, "Erro: " + response.code())
                    } else {
                        val artists = response?.body()
                        Log.d(TAG_LOG, "sendSearchArtist() Sucess")
                        if (artists != null && artists.detail != null) {
                            updateListArtists(artists.detail.items)
                            //for (detail in artists.detail.items!!) {
                            //    Log.d(TAG_LOG, detail.toString())
                            //}
                        }
                    }
                }
            })
        }
    }

    fun onRequestCode() {
        val request = getAuthenticationRequest(AuthenticationResponse.Type.CODE)
        AuthenticationClient.openLoginActivity(this, AUTH_CODE_REQUEST_CODE, request);
    }

    fun onRequestToken() {
        val request = getAuthenticationRequest(AuthenticationResponse.Type.TOKEN)
        AuthenticationClient.openLoginActivity(this, AUTH_TOKEN_REQUEST_CODE, request);
    }

    fun getAuthenticationRequest(type: AuthenticationResponse.Type): AuthenticationRequest {
        return AuthenticationRequest.Builder(CLIENT_ID, type, getRedirectUri().toString())
                .setShowDialog(true)
                .setScopes(arrayOf("user-read-private", "user-read-email"))
                .setCampaign("your-campaign-token")
                .build()
    }

    fun getRedirectUri(): Uri {
        return Uri.Builder()
                .scheme(getString(R.string.com_spotify_sdk_redirect_scheme))
                .authority(getString(R.string.com_spotify_sdk_redirect_host))
                .build()
    }

    var mAccessToken: String? = null
    var mAccessCode: String? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        val response = AuthenticationClient.getResponse(resultCode, data)

        if (AUTH_TOKEN_REQUEST_CODE === requestCode) {
            mAccessToken = "Bearer "+response.accessToken
            sendSearchArtist()
        } else if (AUTH_CODE_REQUEST_CODE === requestCode) {
            mAccessCode = response.code
            sendSearchArtist()
        }
    }
}