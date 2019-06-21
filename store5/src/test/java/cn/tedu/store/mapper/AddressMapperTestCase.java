package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressMapperTestCase {

	@Autowired
	public AddressMapper addressMapper;
	
	@Test
	public void insert() {
		Address address = new Address();
		address.setUid(8888);
		address.setName("root");
		address.setCity("北京");
		Integer rows = addressMapper.insert(address);
		System.err.println(rows);
	}
	
	@Test
	public void countByUid() {
		
		Integer uid = 8888;
		Integer rows = addressMapper.countByUid(uid );
		System.err.println(rows);
	}
}































