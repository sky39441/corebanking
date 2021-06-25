package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.channel.web.testcase.AbstractJacksonBasedTestCase;
import org.junit.Test;

/**
* [DU]샘플계좌원장(JPA).
* <pre>
* [DU]샘플계좌원장(JPA)
* </pre>
* @author admin (Administrator)
* @since 2018-09-14 21:16:22
*/
public class DTB_CBS_XYZ_ACC_M_01_Test extends AbstractJacksonBasedTestCase {
	public DTB_CBS_XYZ_ACC_M_01_Test() {

	}

	/**
	 * Test method for.
	 * @author Administrator(admin)
	 * @since 2018-09-14 21:16:26
	 */
	@Test
	public void i001_T20180914211625654() throws Exception {
		org.jdom2.Element element = doServiceCall();
	}
}
