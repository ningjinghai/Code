package cn.tedu.store.service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.InsertException;

/**
 * 处理收货地址数据的业务层接口
 * @author soft01
 *
 */
public interface IAddressService {
	
	/**
	 * 增加新的收货地址数据
	 * @param address 收货地址数据
	 * @param username 收货人姓名
	 * @throws InsertException
	 */
	void addnew(Address address,String username) throws InsertException;

}
