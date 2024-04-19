public class Figure
{
    static final int ON = 1; // 「ON」
    static final int OFF = 0; // 「OFF」

    static int[][] drawAreaCheck = { //
            { 0, 0, ON } , //左下
            { 0, 100, ON } , //左上
            { 100, 100, ON } , //右上
            { 100, 0, ON } , //右下
            { 0, 0, ON } , //左下
            { 100, 100, ON } , //右上
            { 0, 100, ON } , //左上
            { 100, 0, ON } , //右下
        };

    static int[][] sample = { //
            { 15, 40, OFF } , // 0
            { 65, 85, ON } , //
            { 80, 70, ON } , //
            { 15, 40, ON } , //
            { 45, 50, OFF } , //
            { 45, 10, ON } , //
            { 60, 10, ON } , //
            { 60, 55, ON } , //
        };
}

