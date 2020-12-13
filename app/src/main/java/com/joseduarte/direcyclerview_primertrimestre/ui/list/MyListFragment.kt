package com.joseduarte.direcyclerview_primertrimestre.ui.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.joseduarte.direcyclerview_primertrimestre.R
import com.joseduarte.direcyclerview_primertrimestre.ResourceLoader
import com.joseduarte.direcyclerview_primertrimestre.models.Models
import com.joseduarte.direcyclerview_primertrimestre.models.Person
import com.joseduarte.direcyclerview_primertrimestre.ui.MyItemRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_item_list.*
import org.json.JSONArray
import org.json.JSONObject

class MyListFragment : Fragment() {

    private lateinit var queue: RequestQueue

    private lateinit var personas: MutableList<Models>
    private lateinit var root: View
    private lateinit var prefs: SharedPreferences

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        root = inflater.inflate(R.layout.fragment_item_list, container, false)

        root.findViewById<RecyclerView>(R.id.list).adapter = MyItemRecyclerViewAdapter(listOf())

        queue = Volley.newRequestQueue(context)

        prefs = ResourceLoader.getPreferences()

        make(prefs)
        return root
    }

    override fun onResume() {
        super.onResume()
        make(prefs)
    }

    fun make(pref: SharedPreferences) {
        try {
            personas = mutableListOf<Person>().toMutableList()
            println(pref.contains("dbUrl"))
            val url: String = pref.getString("dbUrl", "http://192.168.1.11/Volley/person.php").toString()
            println(url)
            val stringRequest =
                StringRequest(Request.Method.GET, url, Response.Listener { response ->
                    val jsonArray = JSONArray(response)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = JSONObject(jsonArray.getString(i))
                        val id: String = jsonObject.get("id") as String
                        val name: String = jsonObject.get("name") as String
                        val age: String = jsonObject.get("age") as String

                        personas.add(Person(id, name, age))
                    }

                    var adapter: MyItemRecyclerViewAdapter =
                            root.findViewById<RecyclerView>(R.id.list).adapter as MyItemRecyclerViewAdapter
                    adapter.update(personas)

                }, Response.ErrorListener {
                    it.printStackTrace()
                })

            queue.add(stringRequest)
            queue.start()
        }catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}