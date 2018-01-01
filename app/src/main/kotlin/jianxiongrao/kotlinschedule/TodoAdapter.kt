package jianxiongrao.kotlinschedule

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder

/**
 * author: Jianxiong Rao
 * email:1272670593@qq.com
 * on 2018/1/1
 */
class TodoAdapter(context:Context,realmResults: RealmResults<Todo>,automaticUpdate:Boolean,
                  animateResults:Boolean,private val clickListener:TodoItemClickListener) :RealmBasedRecyclerViewAdapter<Todo,TodoAdapter.ViewHolder>
(context, realmResults,automaticUpdate,animateResults){
    override fun onCreateRealmViewHolder(p0: ViewGroup, p1: Int): ViewHolder{
        val v = inflater.inflate(R.layout.todo_item,p0,false)
        return ViewHolder(v,clickListener)
    }

    override fun onBindRealmViewHolder(holder: ViewHolder, p1: Int) {
        val todo = realmResults[p1]
        holder.todoTitle.setText(todo.title)
        holder.todoTitle.fontFeatureSettings = "font-size:12px"
        holder.todoTitle.setTextColor(Color.argb(255,69,106,124))

        holder.todoContent.setText(todo.content)

    }
    interface TodoItemClickListener{
         fun onClick(caller:View,todo:Todo)
    }
    inner class ViewHolder(view:View,private val clickListener:TodoItemClickListener?):RealmViewHolder(view),View.OnClickListener{
        @BindView(R.id.todo_item_todo_title)
        lateinit var todoTitle:TextView
        @BindView(R.id.todo_item_todo_content)
        lateinit var todoContent:TextView
        init{
            ButterKnife.bind(this,view)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            clickListener?.onClick(v,realmResults[adapterPosition])
        }
    }
}