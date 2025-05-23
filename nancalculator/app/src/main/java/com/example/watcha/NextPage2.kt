package com.example.watcha

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NextPage2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_page2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get button references
        val backButton = findViewById<Button>(R.id.back2button)
        val nextButton = findViewById<Button>(R.id.next2button)

        // Back to MainActivity
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        // Forward to NextPage3
        nextButton.setOnClickListener {
            val intent = Intent(this, NextPage3::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val infoButton = findViewById<ImageButton>(R.id.info_button)
        infoButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage(
                    "Welcome to Watcha!\n\n" +
                            "This app helps you discover K-Drama recommendations based on your preferences.\n\n" +
                            "You can:\n" +
                            "• Browse various K-Dramas through the list.\n" +
                            "• Click on a K-Drama cover to view its details.\n" +
                            "• Tap 'Watch Now' to start watching the selected show.\n\n" +
                            "Enjoy exploring your next favorite K-Drama!"
                )
                .setPositiveButton("OK", null)
                .show()
        }

        val kdramas = listOf(
            Kdrama(
                buttonId = R.id.kdrama7,
                imageResId = R.drawable.htccc,
                title = "Hometown Cha-Cha-Cha",
                synopsisResId = R.string.kdrama7_synopsis,
                watchLink = "https://www.imdb.com/title/tt14518756/?ref_=nv_sr_srsg_0_tt_1_nm_7_in_0_q_Hometown%2520Cha-Cha-Cha"
            ),
            Kdrama(
                buttonId = R.id.kdrama8,
                imageResId = R.drawable.lawschool,
                title = "Law School",
                synopsisResId = R.string.kdrama8_synopsis,
                watchLink = "https://www.imdb.com/title/tt13885336/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_Law%2520School"
            ),
            Kdrama(
                buttonId = R.id.kdrama9,
                imageResId = R.drawable.lovenextdoor,
                title = "Love Next Door",
                synopsisResId = R.string.kdrama9_synopsis,
                watchLink = "https://www.imdb.com/title/tt30446769/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_Love%2520Next%2520Door"
            ),
            Kdrama(
                buttonId = R.id.kdrama10,
                imageResId = R.drawable.residentplaybook,
                title = "Resident Playbook",
                synopsisResId = R.string.kdrama11_synopsis,
                watchLink = "https://www.imdb.com/title/tt31171500/?ref_=nv_sr_srsg_0_tt_3_nm_0_in_0_q_Resident%2520Playbook"

            ),
            Kdrama(
                buttonId = R.id.kdrama11,
                imageResId = R.drawable.moonlovers,
                title = "Moon Lovers: Scarlet Heart Ryeo",
                synopsisResId = R.string.kdrama10_synopsis,
                watchLink = "https://www.imdb.com/title/tt5320412/?ref_=nv_sr_srsg_0_tt_1_nm_0_in_0_q_Moon%2520Lovers%253A%2520Scarlet%2520Heart%2520Ryeo"
            ),
            Kdrama(
                buttonId = R.id.kdrama12,
                imageResId = R.drawable.sweethome,
                title = "Sweet Home",
                synopsisResId = R.string.kdrama12_synopsis,
                watchLink = "https://www.imdb.com/title/tt11612120/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_Sweet%2520Home"
            ),
        )


        for (kdrama in kdramas) {
            val button = findViewById<ImageButton>(kdrama.buttonId)
            button.setOnClickListener {
                showPopup(
                    imageResId = kdrama.imageResId,
                    title = kdrama.title,
                    synopsis = getString(kdrama.synopsisResId),
                    url = kdrama.watchLink
                )
            }
        }
    }

    // ✅ This is now inside the class
    private fun showPopup(imageResId: Int, title: String, synopsis: String, url: String) {
        val popupView = layoutInflater.inflate(R.layout.kdrama_popup, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupView.findViewById<ImageView>(R.id.popup_image).setImageResource(imageResId)
        popupView.findViewById<TextView>(R.id.popup_title).text = title
        popupView.findViewById<TextView>(R.id.popup_synopsis).text = synopsis

        popupView.findViewById<Button>(R.id.watch_now_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            popupWindow.dismiss()
        }

        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)
    }
}