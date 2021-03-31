package com.airbus.oneatlas.sampleapp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.airbus.oneatlas.OneAtlas
import com.airbus.oneatlas.common.listener.IOneAtlasEmptyResponse

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val apikey = getString(R.string.oneatlas_api_key)
        val loginButton = findViewById<FloatingActionButton>(R.id.fab)

        loginButton.setOnClickListener { view ->
            loginButton.isEnabled = false
            OneAtlas.getInstance().authenticateService.login(apikey,
                    "IDP", {
                Snackbar.make(view, "Login successful!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
            }, { exception ->
                loginButton.isEnabled = true
                Snackbar.make(view, "Authentication error: " + exception.code, Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}