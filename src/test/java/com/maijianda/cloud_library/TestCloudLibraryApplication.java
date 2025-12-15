package com.maijianda.cloud_library;

import org.springframework.boot.SpringApplication;

public class TestCloudLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.from(CloudLibraryApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
