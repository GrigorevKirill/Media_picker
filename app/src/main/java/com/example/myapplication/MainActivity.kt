package com.example.myapplication

import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTest.setOnClickListener {
            val dialog = BottomFragment()
            dialog.list = test().toMediaItem()
            dialog.show(supportFragmentManager, null)
        }

    }

    private fun readMedia(): ArrayList<String?> {
        val listOfAllImages = ArrayList<String?>()
        val cursor: Cursor?
        var pathOfImage: String?
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf<String>(
            MediaStore.MediaColumns.DATA,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )

        cursor = contentResolver.query(
            uri, projection, null,
            null, null
        )

        val columnIndexData: Int? = cursor?.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        val columnIndexFolderName: Int? = cursor
            ?.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)
        while (cursor?.moveToNext() == true) {
            pathOfImage = columnIndexData?.let { cursor.getString(it) }
            listOfAllImages.add(pathOfImage)
        }
        return listOfAllImages
    }

    private fun test(): List<String> {
        val galleryImageUrls = mutableListOf<String>()
        val columns = arrayOf(MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media.DATE_TAKEN

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns,
            null, null, "$orderBy DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)

                galleryImageUrls.add(ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id).toString())
            }
        }
        return galleryImageUrls
    }

    private fun List<String?>.toMediaItem(): List<RecyclerViewAdapter.Types.MediaItem> {
        var position = 0
        val result = mutableListOf<RecyclerViewAdapter.Types.MediaItem>()
        result.addAll(
            this.map {
                RecyclerViewAdapter.Types.MediaItem(it, 0, position++)
            }
        )
        return result
    }


}