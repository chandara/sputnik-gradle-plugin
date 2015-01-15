package com.sputnik.gradle.plugin;

import org.gradle.api.Project
import org.gradle.api.Plugin
import pl.touk.sputnik.Connectors
import pl.touk.sputnik.configuration.ConfigurationHolder
import pl.touk.sputnik.configuration.GeneralOption
import pl.touk.sputnik.connector.ConnectorFacade
import pl.touk.sputnik.connector.ConnectorFacadeFactory
import pl.touk.sputnik.review.Engine

public class SputnikPlugin implements Plugin<Project> {

    private Properties mProperties = new Properties();

    @Override
    void apply(Project project) {
        project.extensions.create("sputnik", SputnikPluginExtension, project)

        project.task("sputnik") << {
            setConnectorProperty(GeneralOption.CONNECTOR_TYPE, Connectors.GERRIT)
            setConnectorProperty(GeneralOption.HOST, "${project.sputnik.connectionHost}")
            setConnectorProperty(GeneralOption.PORT, "${project.sputnik.connectionPort}")
            setConnectorProperty(GeneralOption.USE_HTTPS, "${project.sputnik.connectionUseHttps}")
            setConnectorProperty(GeneralOption.USERNAME, "${project.sputnik.connectorUsername}")
            setConnectorProperty(GeneralOption.PASSWORD, "${project.sputnik.connectorPassword}")

            setConnectorProperty(GeneralOption.PROCESS_TEST_FILES, "${project.sputnik.processTestFiles}")
            setConnectorProperty(GeneralOption.MAX_NUMBER_OF_COMMENTS, "${project.sputnik.maxNumberOfComments}")
            setConnectorProperty(GeneralOption.COMMENT_ONLY_CHANGED_LINES, "${project.sputnik.commentOnlyChangedLines}")

            setConnectorProperty(GeneralOption.CHECKSTYLE_ENABLED, "${project.sputnik.checkstyleEnabled}")
            setConnectorProperty(GeneralOption.CHECKSTYLE_CONFIGURATION_FILE, "${project.sputnik.checkstyleConfigurationFile}")

            setConnectorProperty(GeneralOption.PMD_ENABLED, "${project.sputnik.pmdEnabled}")
            setConnectorProperty(GeneralOption.PMD_RULESETS, "${project.sputnik.pmdRulesets}")

            setConnectorProperty(GeneralOption.FINDBUGS_ENABLED, "${project.sputnik.findbugsEnabled}")
            setConnectorProperty(GeneralOption.FINDBUGS_EXCLUDE_FILTER, "${project.sputnik.findbugsExcludeFilter}")
            setConnectorProperty(GeneralOption.FINDBUGS_INCLUDE_FILTER, "${project.sputnik.findbugsIncludeFilter}")

            setConnectorProperty(GeneralOption.SCALASTYLE_ENABLED, "${project.sputnik.scalastyleEnabled}")
            setConnectorProperty(GeneralOption.SCALASTYLE_CONFIGURATION_FILE, "${project.sputnik.scalastyleConfigurationFile}")

            ConfigurationHolder.initFromProperties(mProperties)
            ConnectorFacade facade = ConnectorFacadeFactory.INSTANCE.build(ConfigurationHolder.instance().getProperty(GeneralOption.CONNECTOR_TYPE))
            new Engine(facade).run()
        }
    }

    void setConnectorProperty(String key, String value) {
        if(value != null) {
            mProperties.setProperty(key,value);
        }
    }
}