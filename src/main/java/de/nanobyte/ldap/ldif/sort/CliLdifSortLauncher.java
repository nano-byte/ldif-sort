package de.nanobyte.ldap.ldif.sort;

import com.jdotsoft.jarloader.JarClassLoader;

/**
 * Because Java can't load classes from dependended jars contained into an other
 * jar, we must use an extra class loader that can it.
 *
 * @see <a href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4648386">Simplify deployment and versioning by embedding JAR files within each other</a>
 * @see <a href="http://www.jdotsoft.com/JarClassLoader.php">JarClassLoader</a>
 */
public class CliLdifSortLauncher {

    public static void main(final String[] cliArguments) throws Throwable {
	JarClassLoader jcl = new JarClassLoader();
	jcl.invokeMain("de.nanobyte.ldap.ldif.sort.CliLdifSort", cliArguments);
    }
}
