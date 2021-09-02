import java.io.File
import java.io.FileInputStream
import java.util.*

object Keys {

    @JvmStatic
    fun get(file: File, name: String): String {
        val envValue: String? = System.getenv(
            name.toUpperCase(Locale.getDefault())
                .replace(".", "_")
        )
        return if (envValue.isNullOrEmpty()) {
            if (file.exists()) {
                val properties = Properties()
                properties.load(FileInputStream(file))
                if (properties[name] != null) {
                    return properties[name] as? String
                        ?: throw IllegalArgumentException("Key $name not found.")
                } else {
                    throw IllegalArgumentException("No such property $name in ${file.name}")
                }
            } else {
                throw IllegalArgumentException("Key $name not found in ${file.name}.")
            }
        } else {
            envValue
        }
    }
}