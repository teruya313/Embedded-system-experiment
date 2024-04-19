package hardware;

/**
 * クラス PuchSwitch の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;

public class PushSwitch implements Resettable
{
    private static final GpioController gpio = GpioFactory.getInstance();
    private GpioPinDigitalInput pin;

    public PushSwitch( Pin raspiPin ) {
        pin = gpio.provisionDigitalInputPin( raspiPin );
    }

    @Override
    public void reset() {
    }

    public boolean addListener( PushSwitchListener pswListener ) { 
        if ( pswListener != null ) {
            // System.out.print( "psw.addListener " );
            pin.addListener( new GpioPinListenerDigital() {
                    @Override
                    public void handleGpioPinDigitalStateChangeEvent( GpioPinDigitalStateChangeEvent event ) {
                        // System.out.print( "Act " );
                        pswListener.pushSwitchPerformed();
                    }
                }
            );
            return ( true );
        } else {
            return ( false );
        }
    }

    public boolean isHigh() {
        return ( pin.isHigh() );
    }

    public boolean isLow() {
        return ( pin.isLow() );
    }

    public boolean isPush() {
        return ( pin.isLow() );
    }

    public PinState getState() {
        return ( pin.getState() );
    }
}

