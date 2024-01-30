package test.org.doraemon.common.error;

import org.junit.Assert;
import org.junit.Test;

public class HandleErrorTest {
    Integer shouldPrint(String input){
        if(input == null) {
            return -1;
        }
        System.out.println(input);
        return 0;
    }
    
    public void handleErrorDemo(String x){
        Integer retCode = shouldPrint(x);
        if(retCode.equals(-1)){
            handleNullCondition();
        }else{
            handleOtherCondition();
        }
    }

    private void handleOtherCondition() {
    }

    private void handleNullCondition() {
    }


    @Test
    public void handleErrorByReturnValueIs0(){
        Assert.assertTrue(0 == shouldPrint("123"));
    }

    @Test
    public void handleErrorByReturnValueIs1(){
        Assert.assertTrue(-1 == shouldPrint(null));
    }


    Integer shouldPrint2(String input){
        if(input == null) {
            throw new RuntimeException("input can't be null");
        }
        System.out.println(input);
        return 0;
    }
}
