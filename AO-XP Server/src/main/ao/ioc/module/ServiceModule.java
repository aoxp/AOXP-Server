package ao.ioc.module;

import java.util.Properties;

import ao.service.LoginService;
import ao.service.login.LoginServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ServiceModule extends AbstractModule {

	protected Properties properties;
	
	/**
	 * Creates a new ServiceModule.
	 * @param properties The general project properties.
	 */
	public ServiceModule(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	protected void configure() {
		bind(LoginService.class).to(LoginServiceImpl.class).in(Singleton.class);
	}

}
