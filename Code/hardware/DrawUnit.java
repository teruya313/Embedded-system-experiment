package hardware;

/**
 * クラス DrawUnit の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;

public class DrawUnit implements Resettable
{
    private static DrawUnit drawUnit = new DrawUnit();

    public Servo servoX = new Servo( RaspiPin.GPIO_26 );
    public Servo servoY = new Servo( RaspiPin.GPIO_23 );
    public Laser laser = new Laser( RaspiPin.GPIO_22 );

    private DrawUnit() {
        reset();
    }

    public static DrawUnit getInstance() {
        return ( drawUnit );
    }

    @Override
    public void reset() {
        servoX.reset();
        servoY.reset();
        laser.reset();
    }    

}
