package test.org.doraemon.common.error;

import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.luke.doraemon.common.error.BizException;
import org.luke.doraemon.common.error.BizErrorCode;

public class BizErrorTest {

    @Test
    public void testMessageFormat() {
        Object[] objects = new Object[]{"{1}", "{2}"};
        String format = new MessageFormat("{1}").format(objects);
        System.out.println(format);
    }


    @Test
    public void testBizError() {
        BizException bizException = new BizException(BizErrorCode.TestBaseError, "a small error",
                BizErrorCode.TestBaseError.getErrorCode(), "a small downstream error message");
        System.out.println(bizException.getCode());
        System.out.println(bizException.getMessage());
        System.out.println(bizException.getDownStreamErrorCode());
        System.out.println(bizException.getDownStreamMessage());
        System.out.println(bizException.getDownStreamPrimaryErrorCode());
    }

    public void userNotFound(String userId){
        if(StringUtils.isBlank(userId)){
            throw new BizException(BizErrorCode.InvalidParameter, "userId can't be blank");
        }
        if(StringUtils.equals(userId, "secret user id")){
            // do nothing
        }else{
            throw new BizException(BizErrorCode.UserNotFound, userId);
        }
    }

    @Test
    public void testUserNotFound() {
        try{
            userNotFound("luke");
        }catch (BizException bizException){
            bizException.printStackTrace();
        }
    }

    @Test
    public void testSimpleError() {
        try{
            throw new BizException(BizErrorCode.InvalidParameter, "a simple invalid param");
        }catch (BizException bizException){
            bizException.printStackTrace();
        }
    }
}
