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
 * �����ҵ��ղر������ݿ�
 * */
public class DBMenuInfo {

	private DbManager manager;
	
	public List<MenuEntity> list=new ArrayList<MenuEntity>();
	
	private DBMenuInfo(){
		// �����ǣ� ���ݿ������汾�š����ݿ���¼���������·���� //.setDbDir(new
		// File(Environment.getExternalStorageDirectory().getAbsoluteFile()))
		DbManager.DaoConfig daoConfig = new DaoConfig()
				.setDbName("MYMenu.db")
				.setDbVersion(1)
				.setDbDir(new File(Environment.getExternalStorageDirectory().getAbsolutePath()))
				.setDbUpgradeListener(new DbUpgradeListener() {
					@Override
					public void onUpgrade(DbManager arg0, int arg1, int arg2) {
						System.out.println("���ݿ�����ˣ�");
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
	
	//��ʼ�����
	public void init() {
		try {
			//�ղ��б�
			MenuEntity mce=new MenuEntity();
			manager.save(mce);
			manager.delete(MenuEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**��������*/
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
	
	/**ɾ��*/
	public void Delcollection(MenuEntity me){
		System.out.println("ɾ���ɹ�");
		try {
			manager.delete(MenuEntity.class,WhereBuilder.b("mId", "=",me.getmId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**����*/
	public void SaveCollection(MenuEntity me){//
		try {
			manager.delete(MenuEntity.class,WhereBuilder.b("mId", "=",me.getmId()));
			manager.save(me);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**��ȡ���ݿ�������*/
	public int getSize(){
		List<MenuEntity> list=new ArrayList<MenuEntity>();
		try {
			list=manager.selector(MenuEntity.class).findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list.size();
		
	}
	
	
	/**��ѯ����*/
	public List<MenuEntity> getCollect(){
		List<MenuEntity> list=new ArrayList<MenuEntity>();
		try {
			list=manager.selector(MenuEntity.class).where("care", ">", 0).orderBy("").findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**�ղؼ�������������ݱȽ�*/
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
	
	
	/**ɾ������*/
//	public void deleteCollect(String value){
//		try {
//			manager.delete(MenuEntity.class,WhereBuilder.b("cID", "=",value ));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void updateCollect(String value) { // �������� CityEntity c
//		System.out.println("��������");
//		try {
//			// WhereBuilder�Ĳ�������һ�������������ڶ�������������������������ֵ��
//			manager.update(MenuEntity.class, WhereBuilder.b("cID", "=", value),"foodName");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
