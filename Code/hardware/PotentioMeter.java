package hardware;

/**
 * クラス PotentioMater の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.*;
import com.pi4j.io.i2c.*;
import java.io.*;

public class PotentioMeter implements Resettable
{
    private static I2CBus i2cBus = null;
    private static I2CDevice device= null;
    private int ch;

    private PotentioMeterListener pmtrListener;

    java.util.Timer timer = new java.util.Timer( true );

    private int adValue;
    private int preAdValue;

    public PotentioMeter( int ch ) {
        do {
            try { 
                if ( i2cBus == null ) {
                    i2cBus = I2CFactory.getInstance( I2CBus.BUS_1 );
                }
                if ( device == null ) {
                    device = i2cBus.getDevice( 0x2a );
                }
                this.ch = ch;
            } catch( Exception e ) {
                continue;
            }

        } while ( false );

        timer.schedule( new java.util.TimerTask() {
                @Override
                public void run() {
                    try {
                        adValue = device.read( ch );
                        if ( adValue != preAdValue ) {
                            if ( pmtrListener != null ) {
                                pmtrListener.potentioMeterPerformed();
                            }
                            preAdValue = adValue;
                        }
                    } catch ( Exception e ) {
                        adValue = -1;
                    }
                }
            },10, 10 
        );

    }

    @Override
    public void reset() {
    }

    public boolean addListener( PotentioMeterListener pmtrListener ) {
        if ( pmtrListener != null ) {
            this.pmtrListener = pmtrListener;
            return ( true );
        } else {
            return ( false );
        }
    }

    public int getValue() {
        return ( adValue );

        // try {
        // return ( device.read( ch ) );
        // } catch ( Exception e ) {
        // return ( -1 );
        // }
    }

    public int getValuePerCent() {
        return ( getValue()*100/255 );
    }
}
