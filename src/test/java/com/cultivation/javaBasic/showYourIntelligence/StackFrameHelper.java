package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            return String.format("%s.%s",e.getStackTrace()[1].getClassName(),e.getStackTrace()[1].getMethodName());
        }
        // --end-->
    }
}
