package test.org.doraemon.common.helloworld;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import javax.swing.Spring;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class HelloWorldTest {
    private String changeWordToUpper(String value) {
        return StringUtils.upperCase(value);
    }


    @Test
    public void a_Common_Test(){
        String upperWord = changeWordToUpper("hello");
        System.out.println(upperWord);
    }


    // given
    @Parameters
    public static Collection<String[]> getTestValue(){
        return Lists.newArrayList(new String[][]{
                {"luke", "LUKE"},
                {"", ""},
                {null, null},
                {"CamelCase", "CAMELCASE"},
                {"UPPER", "UPPER"}
        });
    }

    @Parameter(0)
    public String testValue;

    @Parameter(1)
    public String expectValue;

    @Test
    public void a_Good_Test(){
        // when
        String upperWord = changeWordToUpper(testValue);
        // assert
        Assertions.assertThat(upperWord).isEqualTo(expectValue);
    }

    @Test
    public void a_Completed_But_Not_Good_Test(){
        // given
        String testValue1 = "luke";
        String expectValue1 = "LUKE";
        String upperWord1 = changeWordToUpper(testValue1);
        // assert
        Assertions.assertThat(upperWord1).isEqualTo(expectValue1);

        // given
        String testValue2 = null;
        String expectValue2 = null;
        String upperWord2 = changeWordToUpper(testValue2);
        // assert
        Assertions.assertThat(upperWord2).isEqualTo(expectValue2);

        // given
        String testValue3 = "9night";
        String expectValue3 = "9NIGHT";
        String upperWord3 = changeWordToUpper(testValue3);
        // assert
        Assertions.assertThat(upperWord3).isEqualTo(expectValue3);
    }
}
