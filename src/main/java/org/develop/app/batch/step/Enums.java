package org.develop.app.batch.step;

public class Enums {

	public static enum PkgEnums {
		READERS_PKG("readers."),
		PROCESSORS_PKG("processors."),
		WRITERS_PKG("writers."),
		MAPPERS_PKG("mappers."),
		TESKLETS_PKG("tasklets."),
		;
		
		private final static String COMPONENTS_PKG = "org.develop.app.batch.step.components.";
		
		private String value;
		
		private PkgEnums(String value) {
			this.value = value;
		}
		
		public String getValue() {
			
			return COMPONENTS_PKG+value;
		}
		
	}
	public static enum StepEnums {
		READER("reader"),
		PROCESSOR("processor"),
		WRITER("writer"),
		CHUNK("chunk"),
		CLASS_NAME("className"),
		INFO("info"),
		MAPPER_CLASS("mapperClass"),
		
		//DB
		QUERY("query"),
		
		//CSV
		RESOURCE("resource"),
		TOKEN_NAMES("tokenNames"),
		DELIMITER("delimiter"),
		;
		
		private String value;
		
		private StepEnums(String value) {
			this.value = value;
		}
		
		public String getValue() {
			
			return value;
		}
	
	}
	
	
}
