package com.example.jurusandiundip

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvJurusan: RecyclerView
    private val list = ArrayList<Jurusan>()
    private fun showSelectedItem(jurusan: Jurusan) {
        //TODO implicit intent switch to detail activity of selected intent
        Toast.makeText(this, "Anda memilih " + jurusan.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Jurusan-jurusan di UNDIP"

        rvJurusan = findViewById(R.id.rv_jurusan)
        rvJurusan.setHasFixedSize(true)

        list.addAll(getListJurusan())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        selectMenu(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun selectMenu(selectedMenu: Int) {
        when (selectedMenu) {
            R.id.about -> {
                val intentAbout = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAbout)
            }
        }
    }

    @SuppressLint("Recycle")
    private fun getListJurusan(): ArrayList<Jurusan> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataAccreditation = resources.getStringArray(R.array.data_accreditation)
        val dataHistory = resources.getStringArray(R.array.data_history)
        val dataTopicLearned = resources.getStringArray(R.array.data_topic_learned)
        val dataImage = resources.obtainTypedArray(R.array.data_image)
        val dataLink = resources.getStringArray(R.array.data_link)
        val listJurusan = ArrayList<Jurusan>()
        for (i in dataName.indices) {
            val jurusan= Jurusan(dataName[i], dataAccreditation[i], dataHistory[i], dataTopicLearned[i], dataImage.getResourceId(i, -1), dataLink[i])
            listJurusan.add(jurusan)
        }
        return listJurusan
    }

    private fun showRecyclerList() {
        rvJurusan.layoutManager = LinearLayoutManager(this)
        val listJurusanAdapter = ListJurusanAdapter(list)
        rvJurusan.adapter = listJurusanAdapter

        listJurusanAdapter.setOnItemClickCallback(object: ListJurusanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Jurusan) {
                showSelectedItem(data)
                val item = Jurusan(data.name, data.accreditation, data.history ,data.topic_learned, data.image, data.link)
                val moveObjIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveObjIntent.putExtra(DetailActivity.EXTRA_ITEM, item)
                startActivity(moveObjIntent)
            }
        })
    }
}