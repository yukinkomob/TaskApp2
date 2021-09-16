package jp.techacademy.yuki.nishimura.taskapp2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(context: Context) : BaseAdapter() {
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)
    var mTaskList = mutableListOf<Task>()

    override fun getCount(): Int {
        return mTaskList.size
    }

    override fun getItem(position: Int): Any {
        return mTaskList[position]
    }

    override fun getItemId(position: Int): Long {
        return mTaskList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: mLayoutInflater.inflate(R.layout.task_list_item, null)

        val textView1 = view.findViewById<TextView>(R.id.text1)
        val textView2 = view.findViewById<TextView>(R.id.text2)
        val categoryText = view.findViewById<TextView>(R.id.category_text)

        // 後でTaskクラスから情報を取得するように変更する
        textView1.text = mTaskList[position].title

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPANESE)
        val date = mTaskList[position].date
        textView2.text = simpleDateFormat.format(date)

        categoryText.text = mTaskList[position].category

        return view
    }
}