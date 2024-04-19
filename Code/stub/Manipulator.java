package stub;

/**
 * クラス Manipulator の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Manipulator extends JPanel implements Resettable
// public class Manipulator extends Canvas implements Resettable
{
    private static Manipulator manipulator = new Manipulator();

    // public PotentionMeter potX = new PotentionMeter( 0 );
    // public PotentionMeter potY = new PotentionMeter( 1 );
    // public PushSwitch sw1 = new PushSwitch( RaspiPin.GPIO_16 );
    // public PushSwitch sw2 = new PushSwitch( RaspiPin.GPIO_20 );

    public PotentioMeter potX = new PotentioMeter( 0 ,  40 , 130 );
    public PotentioMeter potY = new PotentioMeter( 1 , 160 , 130 );
    public PushSwitch sw1 = new PushSwitch( 1 , 270 , 160 );
    public PushSwitch sw2 = new PushSwitch( 2 , 270 ,  80 );

    private Manipulator() {
        reset();
        setBounds( 500 , 350 , 370 , 280 );
        setBackground( Color.WHITE );
        setLayout( null );

        add( potX );
        add( potY );

        add( sw1 );
        add( sw2 );

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

    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        g.setColor( Color.BLACK );
        g.setFont( new Font( "Arial", Font.BOLD, 28) );
        g.drawString( "X-Y Manipurator" , 30 , 100 );

        potX.repaint();
        potY.repaint();
        sw1.repaint();
        sw2.repaint();

    }
}
