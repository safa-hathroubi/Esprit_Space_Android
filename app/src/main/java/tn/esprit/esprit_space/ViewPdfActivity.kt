package tn.esprit.esprit_space

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView

class ViewPdfActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView
    //val PDF_SELECTION_CODE =100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pdf)

        pdfView = findViewById(R.id.pdfView)
        pdfView.fromAsset("Emploi_21-11-2022.pdf").load()


    }

    /*
    private fun selectPdfFromStorage() {
        Toast.makeText(this@ViewPdfActivity, "Select PDF File", Toast.LENGTH_SHORT).show()
        val  browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(browseStorage, "Select Pdf"), PDF_SELECTION_CODE)
    }

    fun showPdfFromUri(uri: Uri){
        pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null){
            val selectedPdf = data.data
            if (selectedPdf != null) {
                showPdfFromUri(selectedPdf)
            }
        }
    }*/
}