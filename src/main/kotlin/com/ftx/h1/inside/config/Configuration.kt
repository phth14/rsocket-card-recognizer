package com.ftx.h1.inside.config

import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract1
import net.sourceforge.tess4j.util.LoadLibs
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies

@Configuration
@EnableConfigurationProperties(RSocketProperties::class)
class Configuration(val rsocketProps: RSocketProperties) {

    @Bean
    fun tesseractInstance(): ITesseract {
        val tesseractInstance = Tesseract1() // JNA Direct Mapping; for JNA Interface Mapping use: Tesseract()
        val tessDataFolder = LoadLibs.extractTessResources("tessdata")  // Maven build bundles English data
        tesseractInstance.setDatapath(tessDataFolder.path)
        return tesseractInstance
    }

    @Bean
    fun rSocketRequester(builder: RSocketRequester.Builder, rsocketStrategies: RSocketStrategies): RSocketRequester = builder
        .rsocketStrategies(rsocketStrategies)
        .connectTcp("localhost", 4114)
        .block() ?: throw RuntimeException("Error while creating RSocket request")

}
