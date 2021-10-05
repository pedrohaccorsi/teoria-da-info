package com.unisinos.menus;

import com.unisinos.enums.EncodersEnum;
import com.unisinos.enums.EnginesEnum;

import java.util.Scanner;


public class Menu {

    EnginesEnum[] enginesEnums  = EnginesEnum.values();
    EncodersEnum[] encodersEnum = EncodersEnum.values();

    Scanner scanner = new Scanner(System.in);

    public EnginesEnum determineEngine(){

        int selectedOption = -1;

        while (selectedOption < 0 || selectedOption > enginesEnums.length - 1) {
            showOptions(enginesEnums);
            selectedOption = scanner.nextInt();
        }

        return enginesEnums[selectedOption];
    }

    public EncodersEnum determineEncoder(){

        int selectedOption = -1;

        while (selectedOption < 0 || selectedOption > encodersEnum.length - 1) {
            showOptions(encodersEnum);
            selectedOption = scanner.nextInt();
        }

        return encodersEnum[selectedOption];

    }

    private void showEngineOptions(){
        System.out.println("Chose one of the following options:");
        for (int i = 0; i < enginesEnums.length; i++)
            System.out.println("(" + i + ") - " + enginesEnums[i].name());
    }

    private void showOptions(Enum[] options){
        System.out.println("Chose one of the following options:");
        for (int i = 0; i < options.length; i++)
            System.out.println("(" + i + ") - " + options[i].name());
    }

}
