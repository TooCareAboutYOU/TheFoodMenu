package com.zhang.DataBaseDo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.xutils.DbManager;
import org.xutils.x;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.db.sqlite.WhereBuilder;
import com.zhang.entity.MenuEntity;
import android.os.Environment;

/**
 * 创建我的收藏本地数据库
 * */
public class DBMenuInfo {

	private DbManager manager;
	
	public List<MenuEntity> list=new ArrayList<MenuEntity>();
	
	private DBMenuInfo(){
		// 依次是： 数据库名、版本号、数据库更新监听、储存路径、 //.setDbDir(new
		// File(Environment.getExternalStorageDirectory().getAbsoluteFile()))
		DbManager.DaoConfig daoConfig = new DaoConfig()
				.setDbName("MYMenu.db")
				.setDbVersion(1)
				.setDbDir(new File(Environment.getExternalStorageDirectory().getAbsolutePath()))
				.setDbUpgradeListener(new DbUpgradeListener() {
					@Override
					public void onUpgrade(DbManager arg0, int arg1, int arg2) {
						System.out.println("数据库更新了！");
					}
				});
		manager = x.getDb(daoConfig);
	}
	
	private static DBMenuInfo instance;
	public static DBMenuInfo getInstance(){
		if(instance==null){
			instance=new DBMenuInfo();
		}
		return instance;
	};
	
	//初始化表格
	public void init() {
		try {
			//收藏列表
			MenuEntity mce=new MenuEntity();
			manager.save(mce);
			manager.delete(MenuEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**保存数据*/
	public void saveCollect(List<MenuEntity> list){
		List<MenuEntity> alist=new ArrayList<MenuEntity>();
		alist=getCollect();
		System.out.println();
		try {
			if(getSize()==0){
				init();
			}else{
				alist.addAll(list);
				manager.delete(MenuEntity.class);
				manager.save(alist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**删除*/
	public void Delcollection(MenuEntity me){
		System.out.println("删除成功");
		try {
			manager.delete(MenuEntity.class,WhereBuilder.b("mId", "=",me.getmId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**保存*/
	public void SaveCollection(MenuEntity me){//
		try {
			manager.delete(MenuEntity.class,WhereBuilder.b("mId", "=",me.getmId()));
			manager.save(me);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**获取数据库数据条*/
	public int getSize(){
		List<MenuEntity> list=new ArrayList<MenuEntity>();
		try {
			list=manager.selector(MenuEntity.class).findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.size();
		
	}
	
	
	/**查询数据*/
	public List<MenuEntity> getCollect(){
		List<MenuEntity> list=new ArrayList<MenuEntity>();
		try {
			list=manager.selector(MenuEntity.class).where("care", ">", 0).orderBy("").findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**收藏夹数据与加载数据比较*/
	public boolean SelectorDBList(String mID){
		list.clear();
		boolean IsExist=false;
		try {
			list=manager.selector(MenuEntity.class).where("mId", "=",mID).findAll();
			if(list.size()>1){
				IsExist=true;
			}else{
				IsExist=list.get(0).getCare();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IsExist;
	}
	
	
	/**删除数据*/
//	public void deleteCollect(String value){
//		try {
//			manager.delete(MenuEntity.class,WhereBuilder.b("cID", "=",value ));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void updateCollect(String value) { // 更新数据 CityEntity c
//		System.out.println("更新数据");
//		try {
//			// WhereBuilder的参数：第一个（列名）、第二个（运算条件）、第三个（值）
//			manager.update(MenuEntity.class, WhereBuilder.b("cID", "=", value),"foodName");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
