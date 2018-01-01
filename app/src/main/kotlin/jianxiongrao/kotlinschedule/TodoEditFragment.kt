package jianxiongrao.kotlinschedule

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import io.realm.Realm
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find
import java.util.*

/**
 * author: Jianxiong Rao
 * email:1272670593@qq.com
 * on 2018/1/1
 */
class TodoEditFragment: Fragment(){
    val realm:Realm = Realm.getDefaultInstance()
    var todo :Todo ? = null
    companion object{
        val TODO_ID_KEY :String = "todo_id_key"
        fun newInstance(id:String):TodoEditFragment{
            var args:Bundle = Bundle()
            args.putString(TODO_ID_KEY,id)
            var todoEditFragment : TodoEditFragment = newInstance()
            todoEditFragment.arguments = args
            return todoEditFragment
        }
        fun newInstance():TodoEditFragment{
            return TodoEditFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                padding = dip(30)
                var title = editText{
                    id = R.id.todo_title
                    hintResource = R.string.title_hint
                }
                var content = editText{
                    id = R.id.todo_content
                    height = 400
                    hintResource = R.string.content_hint
                }
                button{
                    id = R.id.todo_add
                    textResource = R.string.add_todo
                    textColor = Color.WHITE
                    setBackgroundColor(Color.DKGRAY)
                    onClick { _-> createTodoFrom(title,content) }
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null && arguments.containsKey(TODO_ID_KEY)){
            val todoId = arguments.getString(TODO_ID_KEY)
            todo = realm.where(Todo::class.java).equalTo("id",todoId).findFirst()
            val todoTitle = find<EditText>(R.id.todo_title)
            todoTitle.setText(todo?.title)
            val todoContent = find<EditText>(R.id.todo_content)
            todoContent.setText(todo?.content)
            val add = find<Button>(R.id.todo_add)
            add.setText(R.string.save)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
    /**
     * 新增待办事项，存入Relam数据库中
     */
    private fun createTodoFrom(title:EditText,content:EditText){
        realm.beginTransaction()
        var t = todo?:realm.createObject(Todo::class.java)
        t.id = todo?.id ?: UUID.randomUUID().toString()
        t.title = title.text.toString()
        t.content = content.text.toString()

        realm.commitTransaction()
        //后进先出
        activity.supportFragmentManager.popBackStack()
    }
}

