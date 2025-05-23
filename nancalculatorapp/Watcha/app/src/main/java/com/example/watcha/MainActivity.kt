package com.example.watcha

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var genreSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var generateButton: Button
    private lateinit var happyButton: ImageButton
    private lateinit var sadButton: ImageButton
    private lateinit var chillButton: ImageButton
    private lateinit var resultContainer: LinearLayout
    private lateinit var resultText: TextView
    private lateinit var resultTitle: TextView

    private var selectedMood: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        genreSpinner = findViewById(R.id.genreSpinner)
        timeSpinner = findViewById(R.id.timeSpinner)
        generateButton = findViewById(R.id.generateButton)

        happyButton = findViewById(R.id.happyButton)
        sadButton = findViewById(R.id.sadButton)
        chillButton = findViewById(R.id.chillButton)

        resultContainer = findViewById(R.id.resultContainer)
        resultText = findViewById(R.id.resultText)
        resultTitle = findViewById(R.id.resultTitle)

        resultContainer.visibility = View.GONE

        val genres = listOf("Drama", "Romance", "Comedy", "Anime")
        val genreAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genres)
        genreSpinner.adapter = genreAdapter

        val times = listOf("Short", "Medium", "Long")
        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, times)
        timeSpinner.adapter = timeAdapter

        happyButton.setOnClickListener {
            selectedMood = "Happy"
            Toast.makeText(this, "Mood: Happy", Toast.LENGTH_SHORT).show()
        }

        sadButton.setOnClickListener {
            selectedMood = "Sad"
            Toast.makeText(this, "Mood: Sad", Toast.LENGTH_SHORT).show()
        }

        chillButton.setOnClickListener {
            selectedMood = "Chill"
            Toast.makeText(this, "Mood: Chill", Toast.LENGTH_SHORT).show()
        }

        val infoButton = findViewById<ImageButton>(R.id.infoButton)
        infoButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Welcome to Watcha!")
                .setMessage(
                    "This app helps you discover movie/video recommendations based on your preferences.\n\n" +
                            "How to Use:\n" +
                            "• Choose your mood\n" +
                            "• Choose your preferred genre\n" +
                            "• Choose your preferred time duration\n" +
                            "• Tap 'Generate Movie' to view results\n\n" +
                            "Enjoy watching~"
                )
                .setPositiveButton("OK", null)
                .show()
        }

        val resultContainer = findViewById<LinearLayout>(R.id.resultContainer)
        val closeButton = findViewById<View>(R.id.closeButton)

        closeButton.setOnClickListener {
            resultContainer.visibility = View.GONE
        }

        val movieSuggestions = mapOf(
            Triple("Drama", "Short", "Happy") to listOf("The Lunchbox", "Begin Again", "The Wonderful Story of Henry Sugar", "Modern Times"),
            Triple("Drama", "Short", "Sad") to listOf("Curfew", "Atonement", "The Neighbor’s Window", "The Red Balloon"),
            Triple("Drama", "Short", "Chill") to listOf("Columbus", "Before Sunrise", "Aftersun", "Paterson"),

            Triple("Drama", "Medium", "Happy") to listOf("The Pursuit of Happyness", "Billy Elliot", "The Peanut Butter Falcon", "Dead Poets Society"),
            Triple("Drama", "Medium", "Sad") to listOf("Manchester by the Sea", "Blue Valentine", "The Whale", "Requiem for a Dream"),
            Triple("Drama", "Medium", "Chill") to listOf("Lost in Translation", "Lady Bird", "The Banshees of Inisherin", "The Secret Life of Walter Mitty"),

            Triple("Drama", "Long", "Happy") to listOf("The Green Mile", "Life Is Beautiful", "The Intouchables", "Forrest Gump"),
            Triple("Drama", "Long", "Sad") to listOf("Schindler’s List", "The Boy in the Striped Pajamas", "The Father", "The Pianist"),
            Triple("Drama", "Long", "Chill") to listOf("The Godfather", "The King's Speech", "Nomadland", "Good Will Hunting"),

            Triple("Romance", "Short", "Happy") to listOf("Paperman", "Feast", "Us Again", "The Dot and the Line"),
            Triple("Romance", "Short", "Sad") to listOf("Hotarubi no Mori e", "The Red String", "Canvas", "The Umbrella Man"),
            Triple("Romance", "Short", "Chill") to listOf("In a Heartbeat", "World of Tomorrow", "Out", "The Blue Umbrella"),

            Triple("Romance", "Medium", "Happy") to listOf("La La Land", "10 Things I Hate About You", "Past Lives", "Roman Holiday"),
            Triple("Romance", "Medium", "Sad") to listOf("The Notebook", "Me Before You", "All the Bright Places", "A Walk to Remember"),
            Triple("Romance", "Medium", "Chill") to listOf("Midnight in Paris", "About Time", "The Map of Tiny Perfect Things", "500 Days of Summer"),

            Triple("Romance", "Long", "Happy") to listOf("Pride and Prejudice", "The Vow", "Crazy Stupid Love", "Pretty Woman"),
            Triple("Romance", "Long", "Sad") to listOf("Titanic", "One Day", "Marriage Story", "Brokeback Mountain"),
            Triple("Romance", "Long", "Chill") to listOf("Notting Hill", "Sense and Sensibility", "Call Me by Your Name", "Before Sunset"),

            Triple("Comedy", "Short", "Happy") to listOf("For the Birds", "Presto", "Piper", "One Man Band"),
            Triple("Comedy", "Short", "Sad") to listOf("Drunk History", "Sadcom sketches", "The Present", "The Black Hole"),
            Triple("Comedy", "Short", "Chill") to listOf("Simon’s Cat", "Geri's Game", "Snack Attack", "Lifted"),

            Triple("Comedy", "Medium", "Happy") to listOf("The Intern", "Yes Man", "Free Guy", "School of Rock"),
            Triple("Comedy", "Medium", "Sad") to listOf("Little Miss Sunshine", "Click", "The Farewell", "Mrs. Doubtfire"),
            Triple("Comedy", "Medium", "Chill") to listOf("Napoleon Dynamite", "Chef", "The French Dispatch", "Ferris Bueller's Day Off"),

            Triple("Comedy", "Long", "Happy") to listOf("The Hangover", "Crazy Rich Asians", "Barbie (2023)", "Legally Blonde"),
            Triple("Comedy", "Long", "Sad") to listOf("The Truman Show", "Jojo Rabbit", "The Menu", "Forrest Gump"),
            Triple("Comedy", "Long", "Chill") to listOf("The Grand Budapest Hotel", "Isle of Dogs", "Licorice Pizza", "The Darjeeling Limited"),

            Triple("Anime", "Short", "Happy") to listOf("She and Her Cat", "Tamako Love Story", "My Love Story!! OVA", "Ponyo (shorts)"),
            Triple("Anime", "Short", "Sad") to listOf("Hotarubi no Mori e", "The House of Small Cubes", "To You", "Kanojo to Kanojo no Neko"),
            Triple("Anime", "Short", "Chill") to listOf("La Maison en Petits Cubes", "The Garden of Words", "The Snack World", "Shashinkan"),

            Triple("Anime", "Medium", "Happy") to listOf("Whisper of the Heart", "Kiki's Delivery Service", "My Neighbors the Yamadas", "The Cat Returns"),
            Triple("Anime", "Medium", "Sad") to listOf("A Silent Voice", "I Want to Eat Your Pancreas", "Josee, the Tiger and the Fish", "Your Lie in April"),
            Triple("Anime", "Medium", "Chill") to listOf("Weathering with You", "5 Centimeters per Second", "Children Who Chase Lost Voices", "The Anthem of the Heart"),

            Triple("Anime", "Long", "Happy") to listOf("Your Name", "Howl’s Moving Castle", "Suzume", "Castle in the Sky"),
            Triple("Anime", "Long", "Sad") to listOf("Grave of the Fireflies", "When Marnie Was There", "In This Corner of the World", "Tokyo Magnitude 8.0"),
            Triple("Anime", "Long", "Chill") to listOf("Spirited Away", "The Wind Rises", "Only Yesterday", "Arrietty")
        )

        generateButton.setOnClickListener {
            val selectedGenre = genreSpinner.selectedItem?.toString()
            val selectedTime = timeSpinner.selectedItem?.toString()

            if (selectedMood == null) {
                Toast.makeText(this, "Please select a mood.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val key = Triple(selectedGenre, selectedTime, selectedMood)
            val suggestions = movieSuggestions[key]

            if (suggestions != null) {
                resultContainer.visibility = View.VISIBLE
                resultText.text = suggestions.joinToString("\n• ", prefix = "• ")
            } else {
                resultContainer.visibility = View.VISIBLE
                resultText.text = "No suggestions found for this combination."
            }
        }
    }
}