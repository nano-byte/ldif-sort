package de.nanobyte.ldap.ldif.sort.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.name.Dn;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HierarchicalDnComparatorTest {

    private final Dn left;
    private final Dn right;
    private final int expectedCompareResult;

    public HierarchicalDnComparatorTest(final String leftDn, final String rightDn, final int compareExpectedResult) throws LdapInvalidDnException {
	this.left = new Dn(leftDn);
	this.right = new Dn(rightDn);
	this.expectedCompareResult = compareExpectedResult;
    }

    @Parameters
    public static List<Object[]> data() {
	return Stream.of(
		/* compare(x, x) == 0 */
		new Object[]{"dn=x", "dn=x", 0},
		/* sgn(compare(x, y)) == -sgn(compare(x, y)) */
		new Object[]{"dn=x", "dn=y", -1},
		new Object[]{"dn=y", "dn=x", 1},
		/* ((compare(x, y) > 0) && (compare(y, z) > 0)) => compare(x, z) > 0 */
		new Object[]{"dn=z", "dn=y", 1},
		new Object[]{"dn=y", "dn=x", 1},
		new Object[]{"dn=z", "dn=y", 1},
		/* more rdn's are bigger than less rdns */
		new Object[]{"dn=y, dn=y", "dn=x", 1},
		/* same base rdn, but different end rdn */
		new Object[]{"dn=x, dn=x", "dn=x, dn=y", -1},
		new Object[]{"dn=x, dn=y", "dn=x, dn=x", 1},
		/* dn with more rdns, but same base is smaller */
		new Object[]{"dn=x, dn=y, dn=z", "dn=x,dn=y", 1},
		/* dn with less rdns, but same base is bigger */
		new Object[]{"dn=x, dn=y", "dn=x,dn=y, dn=z", -1}).collect(Collectors.<Object[]>toList());
    }
    
    @Test
    public void testCompare() {
	assertEquals(expectedCompareResult, HierarchicalDnComparator.INSTANCE.compare(left, right));
    }
}