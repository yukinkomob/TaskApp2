package jp.techacademy.yuki.nishimura.taskapp2

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Category: RealmObject(), Serializable {
    var name: String = ""

    // idをプライマリーキーとして設定
    @PrimaryKey
    var id: Int = 0
}
