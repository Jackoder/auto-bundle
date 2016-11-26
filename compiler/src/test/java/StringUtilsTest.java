import com.jackoder.auto.bundle.compiler.util.StringUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2016/11/26
 */
public class StringUtilsTest {

    @Test
    public void testIsJavaClass() {
        Assert.assertTrue(StringUtils.isJavaClassName("java.lang.String"));
        Assert.assertFalse(StringUtils.isJavaClassName("java.lang.String."));
        Assert.assertFalse(StringUtils.isJavaClassName("java..lang.String"));
    }

    @Test
    public void testConstantName() {
        Assert.assertTrue(StringUtils.isConstantName("ABC"));
        Assert.assertTrue(StringUtils.isConstantName("A_BC"));
        Assert.assertTrue(StringUtils.isConstantName("MAIN_ACTIVITY"));
        Assert.assertTrue(StringUtils.isConstantName("_MAINACTIVITY"));
        Assert.assertFalse(StringUtils.isConstantName("abc"));
        Assert.assertFalse(StringUtils.isConstantName("aBc"));
        Assert.assertFalse(StringUtils.isConstantName("main_activity"));
        Assert.assertFalse(StringUtils.isConstantName("_mainactivity"));
        Assert.assertEquals(StringUtils.getConstantName("abc"), "ABC");
        Assert.assertEquals(StringUtils.getConstantName("aBc"), "A_BC");
        Assert.assertEquals(StringUtils.getConstantName("main_activity"), "MAIN_ACTIVITY");
        Assert.assertEquals(StringUtils.getConstantName("_mainactivity"), "_MAINACTIVITY");
    }

    @Test
    public void testSnakeCase() {
        Assert.assertTrue(StringUtils.isSnakeCase("abc"));
        Assert.assertTrue(StringUtils.isSnakeCase("a_bc"));
        Assert.assertTrue(StringUtils.isSnakeCase("main_activity"));
        Assert.assertTrue(StringUtils.isSnakeCase("_mainactivity"));
        Assert.assertFalse(StringUtils.isSnakeCase("aBc"));
        Assert.assertFalse(StringUtils.isSnakeCase("ABC"));
        Assert.assertEquals(StringUtils.getSnakeCase("abc"), "abc");
        Assert.assertEquals(StringUtils.getSnakeCase("aBc"), "a_bc");
        Assert.assertEquals(StringUtils.getSnakeCase("ABC"), "abc");
        Assert.assertEquals(StringUtils.getSnakeCase("main_activity"), "main_activity");
        Assert.assertEquals(StringUtils.getSnakeCase("_mainactivity"), "_mainactivity");
    }

    @Test
    public void testProperCase() {
        Assert.assertEquals(StringUtils.getProperCase("abc"), "Abc");
        Assert.assertEquals(StringUtils.getProperCase("ABC"), "ABC");
        Assert.assertEquals(StringUtils.getProperCase("aBc"), "ABc");
        Assert.assertEquals(StringUtils.getProperCase("mAIN_activity"), "MAIN_activity");
    }

    @Test
    public void testClassName() {
        Assert.assertEquals(StringUtils.getClassName("MAIN_ACTIVITY"), "MainActivity");
        Assert.assertEquals(StringUtils.getClassName("_mainactivity"), "Mainactivity");
        Assert.assertEquals(StringUtils.getClassName("mainActivity_"), "MainActivity_");
    }

    @Test
    public void testVariableName() {
        Assert.assertEquals(StringUtils.getVariableName("MAIN_ACTIVITY"), "mainActivity");
        Assert.assertEquals(StringUtils.getVariableName("_mainactivity"), "mainactivity");
        Assert.assertEquals(StringUtils.getVariableName("mainActivity_"), "mainActivity_");
        Assert.assertEquals(StringUtils.getVariableName("mName"), "name");
        Assert.assertEquals(StringUtils.getVariableName("sName"), "name");
        Assert.assertEquals(StringUtils.getVariableName("_Name"), "name");
        Assert.assertEquals(StringUtils.getVariableName("_name"), "name");
        Assert.assertEquals(StringUtils.getVariableName("OKHttp"), "oKHttp");
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty("null"));
        Assert.assertFalse(StringUtils.isEmpty("abc"));
    }
}
