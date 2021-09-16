package jp.techacademy.yuki.nishimura.taskapp2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import io.realm.Realm
import kotlinx.android.synthetic.main.content_category_input.*

class CategoryInputActivity : AppCompatActivity() {

    companion object {
        const val RESULT_CODE_CATEGORY_INPUT_ADDED = 1000
    }

    private var mCategory: Category? = null

    private val mOnDoneClickListener = View.OnClickListener {
        addCategory()
        setResult(RESULT_CODE_CATEGORY_INPUT_ADDED)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_input)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        register_category_button.setOnClickListener(mOnDoneClickListener)
    }

    private fun addCategory() {
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        mCategory = Category()
        val categoryRealmResults = realm.where(Category::class.java).findAll()
        val identifier: Int =
            if (categoryRealmResults.max("id") != null) {
                categoryRealmResults.max("id")!!.toInt() + 1
            } else {
                0
            }
        mCategory!!.id = identifier

        val category = category_edit_text.text.toString()
        mCategory!!.name = category

        realm.copyToRealmOrUpdate(mCategory!!)

        realm.commitTransaction()
        realm.close()
    }
}