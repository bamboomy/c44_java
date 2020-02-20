package org.bamboomy.c44;

import org.bamboomy.c44.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

		(new Application.Helper()).setPaths();
	}

	private static class Helper implements EnvironmentAware {

		@Autowired
		private Environment env;

		@Override
		public void setEnvironment(final Environment environment) {
			this.env = environment;
		}

		private void setPaths() {

			Board.staticPiecePath = env.getProperty("peace.path");
			Board.staticTomcatPath = env.getProperty("tomcat.path");
		}
	}

}