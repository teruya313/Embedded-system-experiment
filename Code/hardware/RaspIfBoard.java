package hardware;

/**
 * クラス RaspIfBoard の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;

public class RaspIfBoard implements Resettable
{
    private static RaspIfBoard raspIfBoard = new RaspIfBoard();

    public Led[] led = {
            new Led( RaspiPin.GPIO_06 ) ,
            new Led( RaspiPin.GPIO_05 ) ,
            new Led( RaspiPin.GPIO_10 ) ,
            new Led( RaspiPin.GPIO_11 ) ,
        };

    private RaspIfBoard() {
        reset();
    }

    public static RaspIfBoard getInstance() {
        return ( raspIfBoard );
    }

    @Override
    public void reset() {
        for ( int i = 0 ; i < led.length ; i++ ) {
            led[i].reset();
        }
    }

    public void setLedBit( int ch , boolean value ) {
        if ( ch < led.length ) {
            if ( value == true ) {
                led[ch].on();
            } else {
                led[ch].off();
            }
        }
    }

    public void setLedBit( int ch , int value ) {
        if ( ch < led.length ) {
            setLedBit( ch , (value != 0) );
        }
    }

    public int getLedBit( int ch ) {
        int value = 0;
        if ( ch < led.length ) {
            value = led[ch].getValue();
        }
        return ( value );
    }

    public void setLed( int value ) {
        setLed( value , led.length );
    }

    public void setLed( int value , int digit ) {
        int bitScanData = 0x01;
        for ( int i = 0 ; i < digit ; i++ ) {
            if ( (value & bitScanData) == 0 ) {
                led[i].off();
            } else {
                led[i].on();
            }
            bitScanData <<= 1;
        }
    }

    public int getLed() {
        return ( getLed( led.length ) );
    }

    public int getLed( int digit ) {
        int value = 0;
        int bitScanData = 0x01;
        for ( int i = 0; i < digit ; i++ ) {
            if ( led[i].isHigh() ) {
                value |= bitScanData;
            }
            bitScanData <<= 1;
        }
        return ( value );
    }

}
