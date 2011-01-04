package com.grails.plugin.resource.minify

import com.yahoo.platform.yui.compressor.CssCompressor
import com.yahoo.platform.yui.compressor.JavaScriptCompressor
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class MinifyService {

	static transactional = false

	def getMinifiedResourcesConfig() {
			ConfigurationHolder.config.grails.resources.minify
	}

	public void minifyResource(ResourceFileType type, File sourceFile, File targetFile) {
		if (type == ResourceFileType.CSS) {
			writeMinifiedCss sourceFile, targetFile
		} else if (type == ResourceFileType.JS) {
			writeMinifiedJs sourceFile, targetFile
		}
	}

	private void writeMinifiedJs(File sourceFile, File targetFile) {
		String charset = 'UTF-8'
		def jsErrorReporter = new JsErrorReporter()
		Writer out
		try {
			Reader jsIn
			def compressor
			try {
				jsIn = new InputStreamReader(new FileInputStream(sourceFile), charset)
				compressor = new JavaScriptCompressor(jsIn, jsErrorReporter)
			}
			finally {
				close jsIn
			}

			out = new OutputStreamWriter(new FileOutputStream(targetFile), charset)
			compressor.compress out, -1, munge, verbose, preserveAllSemiColons, disableOptimizations
		}
		catch (e) {
			log.error "problem minifying $sourceFile: $e.message"
		}
		finally {
			close out
		}
	}

	private boolean getMunge() {
		return minifiedResourcesConfig.munge  ?: true
	}

	private boolean getPreserveAllSemiColons(){
		return minifiedResourcesConfig.preserveAllSemiColons  ?: false

	}
	private boolean getDisableOptimizations(){
		return minifiedResourcesConfig.disableOptimizations  ?: false

	}
	private boolean getVerbose(){
		return minifiedResourcesConfig.verbose  ?: false

	}


	private void writeMinifiedCss(File sourceFile, File targetFile) {
		String css = sourceFile.text
		String charset = 'UTF-8'
		Writer out
		try {
			def compressor = new CssCompressor(new StringReader(css))
			out = new OutputStreamWriter(new FileOutputStream(targetFile), charset)
			compressor.compress out, -1
		}
		catch (e) {
			log.error "problem minifying $targetFile: $e.message"
		}
		finally {
			if (out) {
				close(out)
			}
		}
	}

	private void close(Closeable closeable) {
		try {
			if (closeable) {
				closeable.close()
			}
		}
		catch (e) {
			// ignored
		}
	}
}