/**
 * クラス Sample の注釈をここに書きます.
 * 
 * @author (あなたの名前)
 * @version (バージョン番号もしくは日付)
 */
import hardware.*;
//import stub.*;

public class Sample
{
    private Hardware hardware = Hardware.getInstance();

    public void sampleMain() {

        hardware.manipulator.sw2.addListener( () -> {
                hardware.reset();
                System.exit( 0 );
            }
        );

        State defState = new DefaultState();
        State s = defState;
        s.setNext( s = new DownState( 0x04 ) );
        s.setNext( s = new DownState( 0x02 ) );
        s.setNext( s = new DownState( 0x01 ) );
        s.setNext( s = new LastState() );
        s.setNext( defState );

        State nowState = defState;
        while ( true ) {
            nowState = nowState.exec();
        }
    }

    private class DefaultState implements State
    {
        boolean stayFlag;

        public DefaultState() {

            hardware.manipulator.sw1.addListener( () -> {
                    System.out.println( "sw1" );
                    stayFlag = false;
                }
            );
        }

        private State next;
        @Override
        public void setNext( State next ) {
            this.next = next;
        }

        @Override
        public State exec() {

            hardware.raspIfBoard.setLed( 0x08 );
            stayFlag = true;
            while ( stayFlag ) {
                wait_ms( 10 );
            }
            System.out.println( "next" );
            return ( next );
        }

    }
    
    private class DownState implements State
    {
        private int value;
        public DownState( int value ) {
            this.value = value;
        }

        private State next;
        @Override
        public void setNext( State next ) {
            this.next = next;
        }

        @Override
        public State exec() {
            hardware.raspIfBoard.setLed( value );
            wait_ms( 1000 );
            return ( next );
        }
    }

    private class LastState implements State
    {
        private State next;
        @Override
        public void setNext( State next ) {
            this.next = next;
        }

        @Override
        public State exec() {
            hardware.raspIfBoard.setLed( 0x0f );
            wait_ms( 2000 );
            return ( next );
        }
    }

    private interface State {
        State exec();

        void setNext( State next );
    }

    private void wait_ms( int waitTime_ms ) {
        try {
            Thread.sleep( waitTime_ms );
        } catch ( Exception e ) {
        }
    }            

}

