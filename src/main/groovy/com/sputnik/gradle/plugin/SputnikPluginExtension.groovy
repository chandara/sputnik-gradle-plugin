package com.sputnik.gradle.plugin;

import org.gradle.api.Project

public class SputnikPluginExtension {
    def String connectionHost
    def int connectionPort
    def boolean connectionUseHttps
    def String connectorUsername
    def String connectorPassword

    def String processTestFiles
    def int maxNumberOfComments
    def boolean commentOnlyChangedLines

    def boolean checkstyleEnabled
    def String checkstyleConfigurationFile

    def boolean pmdEnabled
    def String pmdRulesets

    def boolean findbugsEnabled
    def String findbugsExcludeFilter
    def String findbugsIncludeFilter

    def boolean scalastyleEnabled
    def String scalastyleConfigurationFile

    SputnikPluginExtension(Project project) {
    }
}