package hardware;

/**
 * クラス Servo の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

public class Servo implements Resettable
{
    private static final GpioController gpio = GpioFactory.getInstance();
    private GpioPinPwmOutput pin;
//  private static final int PWM_CLOCK = 400; //400KHz
//  private static final int PWM_RANGE = 5000; //5000us = 5ms
    private static final int PWM_CLOCK = 800; //800KHz
    private static final int PWM_RANGE = 5000; //5000us = 5ms
    private static final int OFFSET = 800;
    private int pwmValue;
    private int min , max;

    public Servo( Pin raspiPin ) {
        pin = gpio.provisionPwmOutputPin( raspiPin );
        reset();
    }

    @Override
    public void reset() {
        Gpio.pwmSetMode( Gpio.PWM_MODE_MS );
//        Gpio.pwmSetClock( 48 ); //19.2MHz / 48 =  400kHz
//        Gpio.pwmSetRange( 2000 ); // 1/400KHz * 2000 = 5ms 
        Gpio.pwmSetClock( 19200 / PWM_CLOCK );
        Gpio.pwmSetRange( PWM_CLOCK * PWM_RANGE / 1000 ); 
        pwmValue = 0;
        pin.setPwm( 0 );
        min = 0;
        max = 255;

    }

    public void setValueUnlimited( int value ) {
        pwmValue = value;
        pin.setPwm( pwmValue + OFFSET );
    }

    public void setMin( int value ) {
        min = value;
    }

    public void setMax( int value ) {
        max = value;
    }

    public void setMinMax( int min , int max ) {
        setMin( min );
        setMax( max );
    }

    public void setValue( int value ) {
        if ( value < min ) {
            value = min;
        }
        if ( max < value ) {
            value = max;
        }
        setValueUnlimited( value );
    }

    public int getValue() {
        return ( pwmValue );
    }

    public int getMin() {
        return ( min );
    }

    public int getMax() {
        return ( max );
    }

}
