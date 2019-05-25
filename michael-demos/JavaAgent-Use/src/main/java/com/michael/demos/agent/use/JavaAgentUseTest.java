package com.michael.demos.agent.use;

/**
 * 类功能描述:
 * <pre>
 *  JVM参数：
 *   -javaagent:D:\Develop\Project\Github\study\NeteaseCourseStudy\michael-demos\JavaAgent-Demo\target\JavaAgent-Demo-1.0.0-RELEASE.jar
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/03 09:30
 */
public class JavaAgentUseTest {

    public static void main(String[] args) {
        System.out.println("JavaAgentTest");

        for (String arg :
                args) {
            System.out.println(arg);
        }
    }
}
