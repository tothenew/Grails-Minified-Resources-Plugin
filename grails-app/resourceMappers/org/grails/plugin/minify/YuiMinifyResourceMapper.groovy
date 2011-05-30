package org.grails.plugin.minify;

import com.grails.plugin.resource.minify.ResourceFileType
import org.grails.plugin.resource.mapper.MapperPhase

class YuiMinifyResourceMapper {

	static defaultExcludes = ['/**/*.png', '/**/*.gif', '/**/*.jpg', '/**/*.jpeg', '/**/*.gz', '/**/*.zip']

	static phase = MapperPhase.COMPRESSION
	def yuiMinifyService

	def map(resource, config) {
		def originalFile = resource.processedFile
		String name = originalFile.name
		ResourceFileType fileType = ResourceFileType.getFileType(name)
		if (fileType in [ResourceFileType.CSS, ResourceFileType.JS]) {
			List<String> tokens = name.tokenize(".")
			String nameWithOutExtension = name - tokens.last()
			String newFilename = nameWithOutExtension + "min.${tokens.last()}"
			def minifiedFile = new File(originalFile.parentFile, newFilename)
			if (true) {
				log.debug "Need to process the file $name and minify it to create ${newFilename}."
				println "Need to process the file $name and minify it to create ${newFilename}."
				yuiMinifyService.minifyResource(fileType, originalFile, minifiedFile)
			} else {
				log.debug "processed file ${newFilename} already exists."
				println "processed file ${newFilename} already exists."
			}
			resource.processedFile = minifiedFile
		}
	}

}
