package us.dev.shipandcargo.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

@Component
public class ObjectUtil {
    public static <T> T objectCopy(Object copyFrom, T copyTo) {
        if (copyFrom != null) {
            BeanCopier beanCopier = BeanCopier.create(copyFrom.getClass(), copyTo.getClass(), false);
            beanCopier.copy(copyFrom, copyTo, null);
        }
        return copyTo;

    }
}
