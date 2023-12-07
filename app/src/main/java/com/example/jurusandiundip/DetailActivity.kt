package com.example.jurusandiundip

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_ITEM, Jurusan::class.java)
        } else {
            @Suppress("DEPRECATED")
            intent.getParcelableExtra(EXTRA_ITEM)
        }

        supportActionBar!!.title = item?.name

        val tvItemName: TextView = findViewById(R.id.tv_item_name)
        val tvItemAccreditation: TextView = findViewById(R.id.tv_item_accreditation)
        val tvItemHistory: TextView = findViewById(R.id.tv_item_history)
        val tvItemTopicLearned: TextView = findViewById(R.id.tv_item_topic_learned)
        val imgItemPhoto: ImageView = findViewById(R.id.img_item_photo)
        if (item != null) {
            tvItemName.text = item.name
            tvItemAccreditation.text = item.accreditation
            tvItemHistory.text = item.history
            tvItemTopicLearned.text = item.topic_learned
            imgItemPhoto.setImageResource(item.image)
        }
        val shareButton: Button = findViewById(R.id.action_share)

        shareButton.setOnClickListener {
            val intentShare = Intent()
            intentShare.action = Intent.ACTION_SEND
            intentShare.putExtra(Intent.EXTRA_TEXT, "${item?.link}")
            intentShare.type = "text/plain"
            startActivity(Intent.createChooser(intentShare, "Bagikan link"))
        }
    }
}