package com.ao.ioc.module;

import java.util.Properties;

import com.ao.security.SecurityManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class SecurityModule extends AbstractModule {

	protected Properties properties;
	
	public SecurityModule(Properties properties) {
		this.properties = properties;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void configure() {
		
		try {
			bind(SecurityManager.class).to((Class<SecurityManager>) Class.forName(properties.getProperty("config.security.manager"))).in(Singleton.class);
			
		} catch (Exception e) {
			throw new RuntimeException("Unexpected error while loading the security manager", e);
		}
	}

}
