package stub;

/**
 * クラス DrawField の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class DrawField extends JPanel implements Resettable
{
    private static final int POINT_SIZE = 4;
    private static DrawField drawField = new DrawField();
    private DrawBuffer drawBuffer;

    java.util.Timer timer = new java.util.Timer( true );

    private DrawField() {
        reset();
        setBounds( 0 , 100 , POINT_SIZE*120 , POINT_SIZE*120 + 100 );
        setBackground( Color.LIGHT_GRAY );
        setLayout( null );

        drawBuffer = drawBufferFactory();

        timer.schedule( new java.util.TimerTask() {
                @Override
                public void run() {
                    repaint();
                }
            },10, 10 
        );

    }

    public static DrawField getInstance() {
        return ( drawField );
    }

    @Override
    public void reset() {
    }    

    @Override
    public void paint( Graphics g ) {
        super.paint( g );
        DrawUnit drawUnit = DrawUnit.getInstance();
        drawUnit.repaint();
        Graphics2D g2 = (Graphics2D)g;

        AffineTransform af = new AffineTransform(1.0d, 0.0d, 0.0d, -1.0d, 0.0d, (double)(POINT_SIZE*120));

        g2.transform( af );
        if ( drawUnit.laser.isOn() ) {
            g.setColor( Color.RED );
        } else {
            g.setColor( Color.LIGHT_GRAY );
        }
        double x = drawUnit.servoX.getValue()*POINT_SIZE*0.7;
        double y = drawUnit.servoY.getValue()*POINT_SIZE*0.7;
        
        g2.fillOval( (int)x, (int)y, POINT_SIZE, POINT_SIZE );

        DrawBuffer now = drawBuffer;
        now.x = (int)x;
        now.y = (int)y;
        drawBuffer = now.next;
        DrawBuffer buf = now;
        while ( buf.next != now ) {
            buf = buf.next;
            // g2.draw( new Line2D.Double( buf.x , buf.y , buf.next.x , buf.next.y ) );
            g2.drawLine( buf.x , buf.y , buf.next.x , buf.next.y );
        }

        g.setColor( Color.BLACK );
        // g.drawString( "LaserUnit" , 10 , 10 );
        g2.drawRect(POINT_SIZE*10,POINT_SIZE*10,POINT_SIZE*100,POINT_SIZE*100);
        // g2.draw( new Rectangle2D.Double(POINT_SIZE*10,POINT_SIZE*10,POINT_SIZE*100,POINT_SIZE*100));
    }

    private class DrawBuffer {
        public int x;
        public int y;
        public DrawBuffer next;
    }
    private DrawBuffer drawBufferFactory() {
        DrawBuffer first = new DrawBuffer();
        DrawBuffer buf = first;
        for ( int i=0 ; i < 100 ; i++ ) {
            buf.next = new DrawBuffer();
            buf = buf.next;
        }
        buf.next = first;
        return ( first );
    }

}
