package jianxiongrao.kotlinschedule

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import io.realm.Realm

/**
 * author: Jianxiong Rao
 * email:1272670593@qq.com
 * on 2018/1/1
 */
class TodosFragment : Fragment(), TodoAdapter.TodoItemClickListener {
    override fun onClick(caller: View, todo: Todo) {
        (activity as MainActivity).hideFab()
        val todoEditFragment = TodoEditFragment.newInstance(todo.id)
        activity.supportFragmentManager.beginTransaction()
                .replace(R.id.content_main,todoEditFragment,todoEditFragment.javaClass.simpleName)
                .addToBackStack(todoEditFragment.javaClass.simpleName)
                .commit()
    }

    @BindView(R.id.todos_recycler_view)
    lateinit var relamRecyclerView:RealmRecyclerView
    private var relam:Realm?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_todos,container,false)
        ButterKnife.bind(this,v)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        relam = Realm.getDefaultInstance()
    }

    override fun onResume() {
        super.onResume()
        val todos = relam!!.where(Todo::class.java).findAll()
        val adapter = TodoAdapter(activity,todos,true,true,this)
        relamRecyclerView.setAdapter(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        relam!!.close()
    }
    companion object{
        fun newInstance():TodosFragment{
            return TodosFragment()
        }
    }
}
