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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AlertDialog


data class Kdrama(
    val buttonId: Int,
    val imageResId: Int,
    val title: String,
    val synopsisResId: Int,
    val watchLink: String
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            WindowInsetsCompat.CONSUMED
        }

        // Navigate to NextPage2 when button is clicked
        val nextButton = findViewById<Button>(R.id.next1button)
        nextButton.setOnClickListener {
            val intent = Intent(this, NextPage2::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

        val infoButton = findViewById<ImageButton>(R.id.info_button)
        infoButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage(
                    "Welcome to Watcha~\n\n" +
                            "Your go-to app for discovering top-rated and must-watch K-Dramas.\n\n" +
                            "How to use:\n" +
                            "• Browse various K-Dramas by switching between pages.\n" +
                            "• Click on a thumbnail to view more details.\n" +
                            "• Tap 'Watch Now' to start watching.\n\n" +
                            "Enjoy\uD83E\uDEF0"
                )
                .setPositiveButton("OK", null)
                .show()
        }

        val kdramas = listOf(
            Kdrama(
                buttonId = R.id.kdrama1,
                imageResId = R.drawable.alchemyofsouls,
                title = "Alchemy of Souls",
                synopsisResId = R.string.kdrama1_synopsis,
                watchLink = "https://www.imdb.com/title/tt20859920/?ref_=ttpl_ov_bk"
            ),
            Kdrama(
                buttonId = R.id.kdrama2,
                imageResId = R.drawable.aouad,
                title = "All of Us Are Dead",
                synopsisResId = R.string.kdrama2_synopsis,
                watchLink = "https://www.imdb.com/title/tt14169960/?ref_=fn_all_ttl_2"
            ),
            Kdrama(
                buttonId = R.id.kdrama3,
                imageResId = R.drawable.businessproposal,
                title = "Business Proposal",
                synopsisResId = R.string.kdrama3_synopsis,
                watchLink = "https://www.imdb.com/title/tt14819828/?ref_=nv_sr_srsg_0_tt_7_nm_0_in_0_q_business%2520proposal"
            ),
            Kdrama(
                buttonId = R.id.kdrama4,
                imageResId = R.drawable.cloy,
                title = "Crash Landing on You",
                synopsisResId = R.string.kdrama4_synopsis,
                watchLink = "https://www.imdb.com/title/tt10850932/?ref_=ttpl_ov_bk"
            ),
            Kdrama(
                buttonId = R.id.kdrama5,
                imageResId = R.drawable.dots,
                title = "Descendant of the Sun",
                synopsisResId = R.string.kdrama5_synopsis,
                watchLink = "https://www.imdb.com/title/tt4925000/?ref_=nv_sr_srsg_2_tt_5_nm_3_in_0_q_Descendant%2520of%2520the%2520Sun"
            ),
            Kdrama(
                buttonId = R.id.kdrama6,
                imageResId = R.drawable.goblin,
                title = "",
                synopsisResId = R.string.kdrama6_synopsis,
                watchLink = "https://www.imdb.com/title/tt5994364/?ref_=nv_sr_srsg_3_tt_8_nm_0_in_0_q_guardian"
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