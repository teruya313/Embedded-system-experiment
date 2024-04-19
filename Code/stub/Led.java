package stub;

/**
 * クラス Led の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Led implements Resettable
{
    // private static final GpioController gpio = GpioFactory.getInstance();
    // private GpioPinDigitalOutput pin;
    protected static final int OFFSET_X = 200;
    protected static final int OFFSET_Y = 120;
    protected static final int SIZE = 20;
    protected static final int PITCH = 30;
    protected int locateX;
    protected int locateY;
    
    protected boolean state = false;
    protected int assign;

    public Led( int assign ) {
        this.assign = assign;
        reset();
        locateX = OFFSET_X - (assign * PITCH);
        locateY = OFFSET_Y;
    }       

    @Override
    public void reset() {
        off();
    }

    public void high() {
        // pin.high();
        state = true;
    }

    public boolean isHigh() {
        // return ( pin.isHigh() );
        return ( state );
    }

    public void low() {
        // pin.low();
        state = false;
    }

    public boolean isLow() {
        // return ( pin.isLow() );
        return ( !state );
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

    // public PinState getState() {
    // return ( pin.getState() );
    // }

    public int getValue() {
        return ( isHigh() ? 1 : 0 );
    }
    
    protected void setLedColor( Graphics g ) {
        if ( isHigh() ) {
            g.setColor( Color.RED );
        } else {
            g.setColor( Color.GRAY );
        }
    }        
    
    public void paint( Graphics g ) {
        setLedColor( g );
        g.fillOval( locateX, locateY, SIZE, SIZE );
        g.setColor( Color.BLACK );
        g.drawOval( locateX, locateY, SIZE, SIZE );

    }
}