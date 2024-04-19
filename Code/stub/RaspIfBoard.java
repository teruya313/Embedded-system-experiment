package stub;

/**
 * クラス RaspIfBoard の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RaspIfBoard extends JPanel implements Resettable
// public class RaspIfBoard extends Canvas implements Resettable
{
    private static RaspIfBoard raspIfBoard = new RaspIfBoard();

    public Led[] led = {
            new Led( 0 ) ,
            new Led( 1 ) ,
            new Led( 2 ) ,
            new Led( 3 ) ,
            new PowerLed( 4 ) ,
        };

    private RaspIfBoard() {
        reset();
        setBounds( 500 , 0 , 300 , 300 );
        setBackground( Color.DARK_GRAY );
        setLed( 10 );
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
            repaint();
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
        repaint();
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

    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        g.setFont( new Font( "Arial", Font.BOLD, 28) );
        g.setColor( Color.WHITE );
        g.drawString( "Rasp I/F" , 100 , 100 );
        for ( int i = 0 ; i < led.length ; i++ ) {
            led[i].paint( g );
        }
    }
}
