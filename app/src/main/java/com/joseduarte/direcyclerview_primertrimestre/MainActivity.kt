package com.joseduarte.direcyclerview_primertrimestre

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.joseduarte.direcyclerview_primertrimestre.models.Models
import com.joseduarte.direcyclerview_primertrimestre.models.Person
import com.joseduarte.direcyclerview_primertrimestre.ui.MyItemRecyclerViewAdapter
import com.joseduarte.direcyclerview_primertrimestre.ui.list.MyListFragment
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ResourceLoader.setContext(this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        prefs = getSharedPreferences("pref_settings", Context.MODE_PRIVATE)

        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) == PackageManager.PERMISSION_DENIED -> {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.INTERNET),
                    1
                )
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                var navContr: NavController = findNavController(R.id.nav_host_fragment)

                println(navContr.currentDestination?.label)
                try {
                    navContr.navigate(R.id.action_home_to_preferences)
                }
                catch (ex: Exception) {}

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}