package stub;

/**
 * クラス DrawUnit の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawUnit extends JPanel implements Resettable
// public class DrawUnit extends Canvas implements Resettable
{
    private static DrawUnit drawUnit = new DrawUnit();

    // public Servo servoX = new Servo( RaspiPin.GPIO_26 );
    // public Servo servoY = new Servo( RaspiPin.GPIO_23 );
    // public Laser laser = new Laser( RaspiPin.GPIO_22 );

    public Servo servoX = new Servo();
    public Servo servoY = new Servo();
    public Laser laser = new Laser();

    private DrawUnit() {
        reset();
        Rectangle df = DrawField.getInstance().getBounds();
        setBounds( df.width/2-100 , 20 , 150 , 50 );
        setBackground( Color.DARK_GRAY );
        setLayout( null );

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

    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        if ( laser.isOn() ) {
            g.setColor( Color.RED );
        } else {
            g.setColor( Color.LIGHT_GRAY );
        }

        g.fillOval( 100 , 25 , 10 , 10 );

        g.setColor( Color.WHITE );
        g.drawString( "LaserUnit" , 20 , 20 );
    }
}
