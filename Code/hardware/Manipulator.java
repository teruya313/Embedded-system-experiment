
package hardware;

/**
 * クラス Manipulator の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;

public class Manipulator implements Resettable
{
    private static Manipulator manipulator = new Manipulator();

    public PotentioMeter potX = new PotentioMeter( 0 );
    public PotentioMeter potY = new PotentioMeter( 1 );
    public PushSwitch sw1 = new PushSwitch( RaspiPin.GPIO_27 );
    public PushSwitch sw2 = new PushSwitch( RaspiPin.GPIO_28 );

    private Manipulator() {
        reset();
    }

    public static Manipulator getInstance() {
        return ( manipulator );
    }

    @Override
    public void reset() {
        potX.reset();
        potY.reset();
        sw1.reset();
        sw2.reset();
    }
}
