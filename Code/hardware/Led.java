package hardware;

/**
 * クラス Led の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;

public class Led implements Resettable
{
    private static final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalOutput pin;

    public Led( Pin raspiPin ) {
        pin = gpio.provisionDigitalOutputPin(raspiPin,PinState.LOW);
        reset();
    }       

    @Override
    public void reset() {
        off();
    }

    public void high() {
        pin.high();
    }

    public boolean isHigh() {
        return ( pin.isHigh() );
    }

    public void low() {
        pin.low();
    }

    public boolean isLow() {
        return ( pin.isLow() );
    }

    public void on() {
        high();
    }

    public boolean isOn() {
        return ( isHigh() );
    }

    public void off() {
        low();
    }

    public boolean isOff() {
        return ( isLow() );
    }

    public PinState getState() {
        return ( pin.getState() );
    }

    public int getValue() {
        return ( isHigh() ? 1 : 0 );
    }
}
