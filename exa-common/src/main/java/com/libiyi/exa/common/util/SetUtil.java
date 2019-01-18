package com.libiyi.exa.common.util;

import java.util.Iterator;
import java.util.Set;

public class SetUtil {

    public static Boolean isSetEqual(Set set1, Set set2) {

        if (set1 == null && set2 == null) {
            return true;
        }

        if (set1 == null || set2 == null || set1.size() != set2.size() || set1.size() == 0 || set2.size() == 0) {
            return false;
        }

        Iterator ite2 = set2.iterator();
        boolean isFullEqual = true;
        while (ite2.hasNext()) {
            if (!set1.contains(ite2.next())) {
                isFullEqual = false;
            }
        }
        return isFullEqual;
    }
}
