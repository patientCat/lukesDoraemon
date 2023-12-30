package test.org.doraemon.common.error;

import java.text.MessageFormat;
import org.junit.Test;
import org.luke.doraemon.common.error.BizException;

public class BizErrorTest {

    @Test
    public void testMessageFormat() {
        Object[] objects = new Object[]{"{1}", "{2}"};
        String format = new MessageFormat("{1}").format(objects);
        System.out.println(format);
    }


    @Test
    public void testBizError() {
        BizException bizException = new BizException(TestErrorCode.TestBaseError, "a small error",
                TestErrorCode.TestBaseError.getErrorCode(), "a small downstream error message");
        System.out.println(bizException.getCode());
        System.out.println(bizException.getMessage());
        System.out.println(bizException.getDownStreamErrorCode());
        System.out.println(bizException.getDownStreamMessage());
        System.out.println(bizException.getDownStreamPrimaryErrorCode());
    }

}
