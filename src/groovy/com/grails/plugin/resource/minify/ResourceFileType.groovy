package com.grails.plugin.resource.minify

public enum ResourceFileType {
	CSS('css'), JS('js'), OTHER('others')
	private final String str;

	ResourceFileType(String str) {
			this.str = str;
	}

	static ResourceFileType getFileType(String fileName) {
		ResourceFileType type = ResourceFileType.OTHER
		List<String> nameTokens = fileName.tokenize(".")
		if (nameTokens.size() > 0) {
			String fileExtension = nameTokens.last().toLowerCase()
			if (fileExtension == 'css') {
				type = ResourceFileType.CSS
			} else if (fileExtension == 'js') {
				type = ResourceFileType.JS
			}
		}
		return type
	}


	public String getKey() { return name() }

	public String getValue() { return toString() }

	public String toString() {
			return str;
	}

}