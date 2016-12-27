package simple.provider;


import org.springframework.stereotype.Component;
import simple.conf.ZooKeeperConfig;
import simple.interfaces.UserService;
import simple.interfaces.UserService1;

import java.util.Map;
import java.util.Random;

@Component
public class UserServiceProvider {
		
	public UserService.Client getBalanceUserService(){
		Map<String, UserService.Client> serviceMap = ZooKeeperConfig.serviceMap;
		//以负载均衡的方式获取服务实例		
		for (Map.Entry<String, UserService.Client> entry : serviceMap.entrySet()) {
			System.out.println("可供选择服务:"+entry.getKey());
		}
		int rand=new Random().nextInt(serviceMap.size());		
		String[] mkeys = serviceMap.keySet().toArray(new String[serviceMap.size()]);
		serviceMap.get(mkeys[rand]);
		return serviceMap.get(mkeys[rand]);
	}
	

}
