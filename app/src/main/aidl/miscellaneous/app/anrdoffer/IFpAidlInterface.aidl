// IFpAidlInterface.aidl
package miscellaneous.app.anrdoffer;
import miscellaneous.app.anrdoffer.bean.I_UserBean_AidlInterface;

// Declare any non-default types here with import statements

interface IFpAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int getPid();

    int getUserId();

    void setUser(in I_UserBean_AidlInterface user, inout Map map);
}
