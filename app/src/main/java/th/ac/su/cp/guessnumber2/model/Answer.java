package th.ac.su.cp.guessnumber2.model;

import android.util.Log;

import java.util.Random;



public class Answer {
    public enum GuessResult{
        OVER,
        UNDER,
        OK
    }
    private int value;

    public Answer(){
        value =  randomValue();
        Log.i("MainActivity","ค่าของ answer ที่สุ่มได้ คือ "+value);
    }


    private int randomValue(){
        return new Random().nextInt(100)+1;
    }

/*
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    */


    public GuessResult checkAnswer(int guess){
        GuessResult result;

        if(guess == value){
            //ทายถูก
            result = GuessResult.OK;
        }else if(guess>value){
            //ทายตัวเลขที่มากเกินไป
            result = GuessResult.OVER;
        }else {
            //ทายตัวเลขที่น้อยกินไป
            result =GuessResult.UNDER;
        }
        return result;
    }
}
