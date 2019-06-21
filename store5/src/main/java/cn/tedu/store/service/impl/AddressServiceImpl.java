package cn.tedu.store.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ex.InsertException;

/**
 * 处理收货地址数据的业务层实现类
 * @author soft01
 *
 */
@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public void addnew(Address address,String username) throws InsertException {
		//根据参数address中的uid执行查询数量
		Integer count = addressMapper.countByUid(address.getUid());
		//判断收货地址数量是否为0
		//是：is_default>1
		//否：is_default>0
		Integer isDefault = count ==0?1:0;
		//将is_default的值封装到参数address中
		address.setIsDefault(isDefault);
		//项目日志数据
		Date now = new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);
		
		//执行增加
		Integer rows = addressMapper.insert(address);
		if(rows!=1) {
			throw new InsertException("增加收货地址失败，插入数据时出现未知错误");
		}
	}

}


















