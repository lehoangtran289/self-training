package com.vds.springbootx.beans;

import org.springframework.boot.autoconfigure.condition.AnyNestedCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * Condition that will match when any nested class condition matches.
 */
public class MyBeanOrPropertyCondition extends AnyNestedCondition {

    public MyBeanOrPropertyCondition(ConfigurationPhase configurationPhase) {
        super(configurationPhase);
    }

    @ConditionalOnBean(MyBean1.class)
    static class MyBean1ExistsCondition {

    }

    @ConditionalOnBean(MyBean2.class)
    static class MyBean2ExistsCondition {

    }

    @ConditionalOnProperty(value = "multipleBeans.enabled", havingValue = "true")
    static class MultipleBeansPropertyExists {

    }
}
