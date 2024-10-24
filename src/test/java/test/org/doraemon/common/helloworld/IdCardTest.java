package test.org.doraemon.common.helloworld;

import java.time.Instant;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class IdCardTest {
    public Long getRetireDayFromIdCard(String idCard){
        boolean valid = validIdCard(idCard);
        if(!valid){
            throw new RuntimeException("id card is illegal");
        }
        // 提取性别
        String sex = getSexFromIdCard(idCard);
        // 提取生日
        Instant birthDay = getBirthdayFromIdCard(idCard);

        // 计算退休天数
        return calRetireDay(sex, birthDay);
    }

    private String getSexFromIdCard(String idCard) {
        return null;
    }

    private Instant getBirthdayFromIdCard(String idCard) {
        return null;
    }

    private Long calRetireDay(String sex, Instant birthDay) {
        return null;
    }

    public boolean validIdCard(String idCard){
        if(StringUtils.isBlank(idCard)){
            return false;
        }
        if(idCard.length() != 18){
            return false;
        }
        return true;
    }

    @Test
    public void a_Common_Test(){
        String testIdCard = "11010519491231002X";
        boolean valid = validIdCard(testIdCard);
        System.out.println(valid);
    }


    @Test
    public void should_idCard_is_legal_success(){
        // given
        String testIdCard = "11010519491231002X";
        // when
        boolean valid = validIdCard(testIdCard);
        // assert
        assert valid == true;
    }

    @Test
    public void should_idCard_is_legal(){
        // given
        String testIdCard = "1";
        // when
        boolean valid = validIdCard(testIdCard);
        // assert
        assert valid == false;
    }
}
