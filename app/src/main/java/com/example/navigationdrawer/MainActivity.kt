package com.example.navigationdrawer
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        binding.appBarMain.switchPatternDarkMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && isCheckedDarkMode.not()){
                darkModeAlert()
            }else if (isChecked.not() && isCheckedDarkMode){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            isCheckedDarkMode = isChecked
        }

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun darkModeAlert() {
        val alertDialog = MaterialAlertDialogBuilder(this)
        alertDialog.setMessage("Dark moda ge??mek istedi??inizden emin misiniz?") // Alert dialogumuzu olu??turuyoruz.
            .setNegativeButton("Hay??r") { dialog, which ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Hay??r(negative button)'a t??kland??????nda dark mode se??imi olmayacakt??r
                binding.appBarMain.switchPatternDarkMode.isChecked = false // Hay??r(negative button)'a t??kland??????nda switch se??imi false olarak atan??yor.
                dialog.dismiss() // Yukar??daki i??lemler ger??ekle??tikten sonra dialog kapan??yor
            }
            .setPositiveButton("Evet") { dialog, which ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) // Evet(positive button)'e t??kland??????nda dark mode se??imi olacakt??r
                dialog.dismiss() // Dialog kapan??yor
            }
        alertDialog.create().apply {
            this.window?.setDimAmount(0.5f) // Dialog a????ld??????nda ekran??n g??lgelendirilmesi sa??lan??yor.
            show()
        }
    }

    companion object{
        var isCheckedDarkMode = false
    }

}