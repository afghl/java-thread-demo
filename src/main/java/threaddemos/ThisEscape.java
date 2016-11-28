package threaddemos;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by afghl on 16/11/26.
 */
class Test {
    private boolean flag = false;

    public Test setFlag() {
        flag = !flag;

        return this;
    }
}

public class ThisEscape {
    public static void main(String[] args) {
        Test t = new Test();
        t = t.setFlag();
    }
}
