package org.grails.plugin.minify;

import com.grails.plugin.resource.minify.ResourceFileType

class MinifyResourceMapper {

	static defaultExcludes = ['/**/*.png', '/**/*.gif', '/**/*.jpg', '/**/*.jpeg', '/**/*.gz', '/**/*.zip']

	def priority = 9000 // after "everything" else is done, but before gzip happens
	def minifyService

	def map(resource, config) {
		def originalFile = resource.processedFile
		String name = originalFile.name
		ResourceFileType fileType = ResourceFileType.getFileType(name)
		if (fileType in [ResourceFileType.CSS, ResourceFileType.JS]) {
			List<String> tokens = name.tokenize(".")
			String nameWithOutExtension = name - tokens.last()
			String newFilename = nameWithOutExtension + "min.${tokens.last()}"
			def minifiedFile = new File(originalFile.parentFile, newFilename)
			if (!minifiedFile.exists()) {
				log.debug "Need to process the file $name and minify it to create ${newFilename}."
				minifyService.minifyResource(fileType, originalFile, minifiedFile)
			} else {
				log.debug "processed file ${newFilename} already exists."
			}
			resource.processedFile = minifiedFile
		}
	}

}
