class MinifiedResourcesGrailsPlugin {
    // the plugin version
	def version = "1.0 RC1"

	def grailsVersion = "1.2 > *"

	def dependsOn = [resources:'1.0-alpha11 > *']

	def loadAfter = ['resources']

    // resources that are excluded from plugin packaging
    def pluginExcludes = [
				"grails-app/conf/**/*.*",
				"grails-app/controllers/**/*.*",
				"grails-app/domain/**/*.*",
				"grails-app/taglib/**/*.*",
				"grails-app/utils/**/*.*",
				"grails-app/views/**/*.*",
				"web-app/css/**/*.*",
				"web-app/js/**/*.*"
    ]

    def author = '<a href="http://www.intelligrape.com/blog/author/himanshu/">Himanshu Seth</a>'
    def authorEmail = "himanshu@intelligrape.com"
    def title = "Minified Resources"
    def description = '''\\
Minifies the css and js static resources using the Resources plugin framework. The minification process uses the
<a href="http://yuilibrary.com/projects/yuicompressor/">YUI Compression</a>(version 2.4.2) to compress the css and js files.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/minified-resources"

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
