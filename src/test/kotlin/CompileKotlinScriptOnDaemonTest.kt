
import org.jetbrains.kotlin.daemon.client.KotlinCompilerClient
import org.testng.annotations.Test
import java.io.File
import java.net.URLClassLoader


class CompileKotlinScriptForFutureUseTest {

    val rootDir: String = System.getProperty("user.dir")

    @Test
    fun compileScriptWithCompilerClient() {
        KotlinCompilerClient.main(
            "--daemon-shutdownDelayMilliseconds=7200",
            "--daemon-autoshutdownIdleSeconds=7200",
            "--daemon-autoshutdownUnusedSeconds=7200",
            """--daemon-compilerClasspath=
/opt/kotlinc/lib/kotlin-compiler.jar:
/opt/kotlinc/lib/kotlin-daemon.jar:
/opt/kotlinc/lib/kotlin-reflect.jar:
/opt/kotlinc/lib/kotlin-stdlib.jar:
/opt/kotlinc/lib/kotlin-script-runtime.jar:
/opt/kotlinc/lib/trove4j.jar:
/opt/kotlinc/lib/annotations-13.0.jar:
/opt/kotlinc/lib/kotlin-scripting-compiler.jar:
/opt/kotlinc/lib/kotlin-scripting-compiler-impl.jar:
/opt/kotlinc/lib/kotlinx-coroutines-core-jvm.jar:
/opt/kotlinc/lib/kotlin-scripting-common.jar:
/opt/kotlinc/lib/kotlin-scripting-jvm.jar:
/opt/kotlinc/lib/kotlin-scripting-js.jar:
/opt/kotlinc/lib/js.engines.jar
                """.trimIndent().trim().replace("\n".toRegex(), "")
            , "-d", rootDir
            , "$rootDir/src/test/resources/my.kts"
        )

        val cl: ClassLoader = URLClassLoader(arrayOf(File(rootDir).toURL()))
        val myClass = cl.loadClass("My")
        val myClassInst = myClass.constructors.get(0).newInstance(arrayOf("myarg"))
        val result = myClass.declaredMethods.get(0).invoke(myClassInst, arrayOf("myarg"))

        println("script invocation returned: $result")
    }
}