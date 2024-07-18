package com.ilham.superhero

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var fullNameTextView: TextView
    private lateinit var intelligenceTextView: TextView
    private lateinit var strengthTextView: TextView
    private lateinit var speedTextView: TextView
    private lateinit var durabilityTextView: TextView
    private lateinit var powerTextView: TextView
    private lateinit var combatTextView: TextView
    private lateinit var aliasesTextView: TextView
    private lateinit var placeOfBirthTextView: TextView
    private lateinit var firstAppearanceTextView: TextView
    private lateinit var publisherTextView: TextView
    private lateinit var alignmentTextView: TextView
    private lateinit var genderTextView: TextView
    private lateinit var raceTextView: TextView
    private lateinit var heightTextView: TextView
    private lateinit var weightTextView: TextView
    private lateinit var eyeColorTextView: TextView
    private lateinit var hairColorTextView: TextView
    private lateinit var occupationTextView: TextView
    private lateinit var baseTextView: TextView
    private lateinit var groupAffiliationTextView: TextView
    private lateinit var relativesTextView: TextView
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById(R.id.nameTextView)
        fullNameTextView = findViewById(R.id.fullNameTextView)
        intelligenceTextView = findViewById(R.id.intelligenceTextView)
        strengthTextView = findViewById(R.id.strengthTextView)
        speedTextView = findViewById(R.id.speedTextView)
        durabilityTextView = findViewById(R.id.durabilityTextView)
        powerTextView = findViewById(R.id.powerTextView)
        combatTextView = findViewById(R.id.combatTextView)
        aliasesTextView = findViewById(R.id.aliasesTextView)
        placeOfBirthTextView = findViewById(R.id.placeOfBirthTextView)
        firstAppearanceTextView = findViewById(R.id.firstAppearanceTextView)
        publisherTextView = findViewById(R.id.publisherTextView)
        alignmentTextView = findViewById(R.id.alignmentTextView)
        genderTextView = findViewById(R.id.genderTextView)
        raceTextView = findViewById(R.id.raceTextView)
        heightTextView = findViewById(R.id.heightTextView)
        weightTextView = findViewById(R.id.weightTextView)
        eyeColorTextView = findViewById(R.id.eyeColorTextView)
        hairColorTextView = findViewById(R.id.hairColorTextView)
        occupationTextView = findViewById(R.id.occupationTextView)
        baseTextView = findViewById(R.id.baseTextView)
        groupAffiliationTextView = findViewById(R.id.groupAffiliationTextView)
        relativesTextView = findViewById(R.id.relativesTextView)
        imageView = findViewById(R.id.imageView)

        fetchSuperheroData()
    }

    private fun fetchSuperheroData() {
        RetrofitInstance.api.getSuperhero().enqueue(object : Callback<Superhero> {
            override fun onResponse(call: Call<Superhero>, response: Response<Superhero>) {
                if (response.isSuccessful) {
                    val superhero = response.body()
                    superhero?.let {
                        displaySuperheroData(it)
                    }
                }
            }

            override fun onFailure(call: Call<Superhero>, t: Throwable) {
            }
        })
    }

    private fun displaySuperheroData(superhero: Superhero) {
        nameTextView.text = superhero.name
        fullNameTextView.text = superhero.biography.fullName
        intelligenceTextView.text = "Intelligence: ${superhero.powerstats.intelligence}"
        strengthTextView.text = "Strength: ${superhero.powerstats.strength}"
        speedTextView.text = "Speed: ${superhero.powerstats.speed}"
        durabilityTextView.text = "Durability: ${superhero.powerstats.durability}"
        powerTextView.text = "Power: ${superhero.powerstats.power}"
        combatTextView.text = "Combat: ${superhero.powerstats.combat}"
        aliasesTextView.text = "Aliases: ${superhero.biography.aliases.joinToString(", ")}"
        placeOfBirthTextView.text = "Place of Birth: ${superhero.biography.placeOfBirth}"
        firstAppearanceTextView.text = "First Appearance: ${superhero.biography.firstAppearance}"
        publisherTextView.text = "Publisher: ${superhero.biography.publisher}"
        alignmentTextView.text = "Alignment: ${superhero.biography.alignment}"
        genderTextView.text = "Gender: ${superhero.appearance.gender}"
        raceTextView.text = "Race: ${superhero.appearance.race}"
        heightTextView.text = "Height: ${superhero.appearance.height.joinToString(", ")}"
        weightTextView.text = "Weight: ${superhero.appearance.weight.joinToString(", ")}"
        eyeColorTextView.text = "Eye Color: ${superhero.appearance.eyeColor}"
        hairColorTextView.text = "Hair Color: ${superhero.appearance.hairColor}"
        occupationTextView.text = "Occupation: ${superhero.work.occupation}"
        baseTextView.text = "Base: ${superhero.work.base}"
        groupAffiliationTextView.text = "Group Affiliation: ${superhero.connections.groupAffiliation}"
        relativesTextView.text = "Relatives: ${superhero.connections.relatives}"
        Glide.with(this).load(superhero.image.url).into(imageView)
    }
}
