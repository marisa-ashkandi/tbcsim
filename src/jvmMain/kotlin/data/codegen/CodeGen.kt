package data.codegen

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import data.codegen.generators.ItemGen

object CodeGen {
    private val mapper = ObjectMapper().registerKotlinModule()

    val outPath: String = "${System.getProperty("user.dir")}/src/commonMain/kotlin/"

    fun <T> load(file: String, type: TypeReference<T>): T {
        // Load
        val data = CodeGen::class.java.getResourceAsStream(file).readAllBytes().decodeToString()
        return mapper.readValue(data, type)
    }

    fun generate() {
        ItemGen.generate()
    }
}
