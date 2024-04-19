package hardware;

/**
 * クラス Laser の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;

public class Laser implements Resettable
{
    private static final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput pin;
    private boolean state;

    public Laser( Pin raspiPin ) {
        pin = gpio.provisionDigitalOutputPin(raspiPin,PinState.LOW);
        reset();
    }       

    @Override
    public void reset() {
        off();
    }

    public void on() {
        pin.low();
        state = true;
    }

    public void off() {
        pin.high();
        state = false;
    }

    public boolean isOn() {
        return ( state );
    }

    public boolean isOff() {
        return ( !state );
    }

}
