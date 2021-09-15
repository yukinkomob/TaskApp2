package jp.techacademy.yuki.nishimura.taskapp2

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mRealm: Realm
    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reloadListView()
        }
    }

    private lateinit var mTaskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        mTaskAdapter = TaskAdapter(this)

        listView1.setOnItemClickListener { parent, view, position, id ->
            // 入力・編集する画面に遷移させる
        }

        listView1.setOnItemLongClickListener { parent, view, position, id ->
            // タスクを削除する
            true
        }

        addTaskForTest()

        reloadListView()
    }

    private fun reloadListView() {
        val taskRealmResults =
            mRealm.where(Task::class.java).findAll().sort("date", Sort.DESCENDING)
        mTaskAdapter.mTaskList = mRealm.copyFromRealm(taskRealmResults)
        listView1.adapter = mTaskAdapter
        mTaskAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

    private fun addTaskForTest() {
        val task = Task()
        task.title = "作業"
        task.contents = "プログラムを書いてPUSHする"
        task.date = Date()
        task.id = 0
        mRealm.beginTransaction()
        mRealm.copyToRealmOrUpdate(task)
        mRealm.commitTransaction()
    }
}