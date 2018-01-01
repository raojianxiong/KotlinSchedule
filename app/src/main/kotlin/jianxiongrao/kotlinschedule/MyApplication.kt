package jianxiongrao.kotlinschedule

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * author: Jianxiong Rao
 * email:1272670593@qq.com
 * on 2018/1/1
 */
class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder(this).name("realm.my_todos")
                .encryptionKey(getKey())
                .schemaVersion(1).deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }
    /**
     * 64 bits
     */
    private fun getKey():ByteArray{
        return byteArrayOf(0,1,2,3,4,3,2,1,0,1,2,3,4,3,2,1,0,1,2,3,4,3,2,1,0,1,2,3,4,3,2,1,0,
                1,2,3,4,3,2,1,0,1,2,3,4,3,2,1,0,1,2,3,4,3,2,1,0,1,2,3,4,3,2,1)
    }
}
