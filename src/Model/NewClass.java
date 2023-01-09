/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author EXPERTS
 */


public class NewClass {
    public static void main(String[] args) {
        for(int i=0 ; i<100;i++)
           System.err.println(getRandomNumNotZero(-1, 1));
     
    
    }
    
        private static int getRandomNum(int min, int  max){
        return (int) (Math.random()*(max-min)+min);
    }    
    private static int getRandomNumNotZero(int min, int  max){
        int temp = (int) (Math.random()*2);
        if(temp==0)
            return (int) (Math.random()*(-1-min)+min);
        else return (int) (Math.random() *(max-1)+1);
    }    
    
}
