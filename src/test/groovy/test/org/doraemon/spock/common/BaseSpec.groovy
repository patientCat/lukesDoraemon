package test.org.doraemon.spock.common

import spock.lang.Specification

class BaseSpec extends Specification{
    def "test"(){
        given:
        def x = 1
        when:
        println("hello")
        then: 1 == 1
    }
}
