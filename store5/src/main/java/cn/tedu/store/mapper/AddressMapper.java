package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;
/**
 * 处理收货地址的持久层接口
 * @author soft01
 *
 */
public interface AddressMapper {
	
	Integer insert(Address address);

	Integer countByUid(Integer uid);
}
