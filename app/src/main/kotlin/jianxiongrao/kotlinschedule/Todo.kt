package jianxiongrao.kotlinschedule

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * author: Jianxiong Rao
 * email:1272670593@qq.com
 * on 2018/1/1
 */
@RealmClass
open class Todo : RealmObject() {
    @PrimaryKey
    open var id:String = "-1"
    open var title:String = "日程"
    open var content:String = "事项"
}