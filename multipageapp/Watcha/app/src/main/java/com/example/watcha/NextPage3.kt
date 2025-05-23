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

class NextPage3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_next_page3)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backButton = findViewById<Button>(R.id.back3button)

        // Back to NextPage2
        backButton.setOnClickListener {
            val intent = Intent(this, NextPage2::class.java)
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
                buttonId = R.id.kdrama13,
                imageResId = R.drawable.touchyourheart,
                title = "Touch Your Heart",
                synopsisResId = R.string.kdrama13_synopsis,
                watchLink = "https://www.imdb.com/title/tt9466990/?ref_=nv_sr_srsg_0_tt_5_nm_0_in_0_q_Touch%2520Your%2520Heart "
            ),
            Kdrama(
                buttonId = R.id.kdrama14,
                imageResId = R.drawable.truebeauty,
                title = "True Beauty",
                synopsisResId = R.string.kdrama14_synopsis,
                watchLink = "https://www.imdb.com/title/tt13274038/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_True%2520Beauty "
            ),
            Kdrama(
                buttonId = R.id.kdrama15,
                imageResId = R.drawable.twinklingwatermelon,
                title = "Twinkling Watermelon",
                synopsisResId = R.string.kdrama15_synopsis,
                watchLink = "https://www.imdb.com/title/tt27446493/?ref_=ttpl_ov_bk"
            ),
            Kdrama(
                buttonId = R.id.kdrama16,
                imageResId = R.drawable.vincenzo,
                title = "Vincenzo",
                synopsisResId = R.string.kdrama16_synopsis,
                watchLink = "https://www.imdb.com/title/tt13433812/?ref_=nv_sr_srsg_0_tt_1_nm_7_in_0_q_Vincenzo"
            ),
            Kdrama(
                buttonId = R.id.kdrama17,
                imageResId = R.drawable.wlgyt,
                title = "When Life Gives You Tangerines",
                synopsisResId = R.string.kdrama17_synopsis,
                watchLink = "https://www.imdb.com/title/tt26471411/?ref_=nv_sr_srsg_0_tt_8_nm_0_in_0_q_When%2520Life%2520Gives%2520You%2520Tangerines"
            ),
            Kdrama(
                buttonId = R.id.kdrama18,
                imageResId = R.drawable.wwwsk,
                title = "What\'s Wrong with Secretary Kim",
                synopsisResId = R.string.kdrama18_synopsis,
                watchLink = "https://www.imdb.com/title/tt8242904/?ref_=nv_sr_srsg_0_tt_5_nm_0_in_0_q_What%255C%27s%2520Wrong%2520with%2520Secretary%2520Kim"
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