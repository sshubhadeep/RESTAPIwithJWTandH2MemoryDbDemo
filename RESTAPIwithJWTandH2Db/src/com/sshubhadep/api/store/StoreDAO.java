package com.sshubhadep.api.store;

import java.util.ArrayList;
import java.util.List;

import com.sshubhadep.api.h2db.H2MemoryDatabase;

public class StoreDAO {

	public List<Store> getAllStores(){

		List<Store> storeList = new ArrayList<Store>();

		try{

			storeList = H2MemoryDatabase.getAllStores();

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}

		return storeList;
	}

	public int saveStore(Store store){

		try{

			H2MemoryDatabase.saveStore(store);

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
			return -1;
		}

		return 1;
	}

	public int updateStore(Store store) {

		List<Store> storeList = getAllStores();

		for(Store myStore: storeList){
			if(myStore.getStoreID() == store.getStoreID()){
				H2MemoryDatabase.updateStore(store);
				return 1;
			}
		}		
		return -1;
	}

	public Store getStore(int id){
		List<Store> stores = getAllStores();

		for(Store store: stores){
			if(store.getStoreID() == id){
				return store;
			}
		}
		return null;
	}

	public int createStore(Store store) {
		
		List<Store> stores = getAllStores();
		boolean storeExists = false;

		for(Store mystore: stores){
			if(store.getStoreID() == mystore.getStoreID()){
				storeExists = true;
				break;
			}
		}
		
		if(!storeExists){
			saveStore(store);
			return 1;
		}
		
		return -1;
	}
	
	public int deleteStore(int id){
	      List<Store> storeList = getAllStores();

	      for(Store store: storeList){
	         if(store.getStoreID() == id){
	            deleteStore(store);
	            return 1;   
	         }
	      }		
	      return -1;
	   }

	public void deleteStore(Store store) {
		
		try{

			H2MemoryDatabase.deleteStore(store);

		} catch (Exception e) {
			System.out.println("Exception Message " + e.getLocalizedMessage());
		}		
	}
}
