package eccrm.base.drug.dao.impl;

import java.util.List;

/**
 * Created by wo on 2016/8/20.
 */
public class ListToStringUtil {
    public static String listToString(List<Object> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (int i=0;i<stringList.size();i++) {
            String s= (String) stringList.get(i);
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(s);
        }
        return result.toString();
    }
}
