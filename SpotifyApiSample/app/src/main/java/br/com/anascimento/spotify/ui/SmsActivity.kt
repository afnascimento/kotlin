package br.com.anascimento.spotify.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.anascimento.spotify.R
import kotlinx.android.synthetic.main.activity_search.*

/**
 * Created by andre.nascimento on 15/09/2017.
 */
class SmsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}