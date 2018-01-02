package com.ibbtek.utilities;

import java.text.Normalizer;

/*
 * The MIT License
 *
 * ArabicNormalizer
 *
 * Copyright 2015 Ibbtek <http://ibbtek.altervista.org/>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

/**
 * ArabicNormalizer class
 *
 * @author Ibbtek <ibbtek@gmail.com>
 */
public final class ArabicNormalizer {

    private final String output;
    private String input;

    /**
     * ArabicNormalizer constructor
     *
     * @param input String
     */
    public ArabicNormalizer(String input) {
        this.input = input;
        this.output = normalize();
    }

    /**
     * normalize Method
     *
     * @return String
     */
    private String normalize() {

        //Remove honorific sign
        input = input.replaceAll("\u0610", "");//ARABIC SIGN SALLALLAHOU ALAYHE WA SALLAM
        input = input.replaceAll("\u0611", "");//ARABIC SIGN ALAYHE ASSALLAM
        input = input.replaceAll("\u0612", "");//ARABIC SIGN RAHMATULLAH ALAYHE
        input = input.replaceAll("\u0613", "");//ARABIC SIGN RADI ALLAHOU ANHU
        input = input.replaceAll("\u0614", "");//ARABIC SIGN TAKHALLUS

        //Remove koranic anotation
        input = input.replaceAll("\u0615", "");//ARABIC SMALL HIGH TAH
        input = input.replaceAll("\u0616", "");//ARABIC SMALL HIGH LIGATURE ALEF WITH LAM WITH YEH
        input = input.replaceAll("\u0617", "");//ARABIC SMALL HIGH ZAIN
        input = input.replaceAll("\u0618", "");//ARABIC SMALL FATHA
        input = input.replaceAll("\u0619", "");//ARABIC SMALL DAMMA
        input = input.replaceAll("\u061A", "");//ARABIC SMALL KASRA
        input = input.replaceAll("\u06D6", "");//ARABIC SMALL HIGH LIGATURE SAD WITH LAM WITH ALEF MAKSURA
        input = input.replaceAll("\u06D7", "");//ARABIC SMALL HIGH LIGATURE QAF WITH LAM WITH ALEF MAKSURA
        input = input.replaceAll("\u06D8", "");//ARABIC SMALL HIGH MEEM INITIAL FORM
        input = input.replaceAll("\u06D9", "");//ARABIC SMALL HIGH LAM ALEF
        input = input.replaceAll("\u06DA", "");//ARABIC SMALL HIGH JEEM
        input = input.replaceAll("\u06DB", "");//ARABIC SMALL HIGH THREE DOTS
        input = input.replaceAll("\u06DC", "");//ARABIC SMALL HIGH SEEN
        input = input.replaceAll("\u06DD", "");//ARABIC END OF AYAH
        input = input.replaceAll("\u06DE", "");//ARABIC START OF RUB EL HIZB
        input = input.replaceAll("\u06DF", "");//ARABIC SMALL HIGH ROUNDED ZERO
        input = input.replaceAll("\u06E0", "");//ARABIC SMALL HIGH UPRIGHT RECTANGULAR ZERO
        input = input.replaceAll("\u06E1", "");//ARABIC SMALL HIGH DOTLESS HEAD OF KHAH
        input = input.replaceAll("\u06E2", "");//ARABIC SMALL HIGH MEEM ISOLATED FORM
        input = input.replaceAll("\u06E3", "");//ARABIC SMALL LOW SEEN
        input = input.replaceAll("\u06E4", "");//ARABIC SMALL HIGH MADDA
        input = input.replaceAll("\u06E5", "");//ARABIC SMALL WAW
        input = input.replaceAll("\u06E6", "");//ARABIC SMALL YEH
        input = input.replaceAll("\u06E7", "");//ARABIC SMALL HIGH YEH
        input = input.replaceAll("\u06E8", "");//ARABIC SMALL HIGH NOON
        input = input.replaceAll("\u06E9", "");//ARABIC PLACE OF SAJDAH
        input = input.replaceAll("\u06EA", "");//ARABIC EMPTY CENTRE LOW STOP
        input = input.replaceAll("\u06EB", "");//ARABIC EMPTY CENTRE HIGH STOP
        input = input.replaceAll("\u06EC", "");//ARABIC ROUNDED HIGH STOP WITH FILLED CENTRE
        input = input.replaceAll("\u06ED", "");//ARABIC SMALL LOW MEEM

        //Remove tatweel
        input = input.replaceAll("\u0640", "");

        //Remove tashkeel
        input = input.replaceAll("\u064B", "");//ARABIC FATHATAN
        input = input.replaceAll("\u064C", "");//ARABIC DAMMATAN
        input = input.replaceAll("\u064D", "");//ARABIC KASRATAN
        input = input.replaceAll("\u064E", "");//ARABIC FATHA
        input = input.replaceAll("\u064F", "");//ARABIC DAMMA
        input = input.replaceAll("\u0650", "");//ARABIC KASRA
        input = input.replaceAll("\u0651", "");//ARABIC SHADDA
        input = input.replaceAll("\u0652", "");//ARABIC SUKUN
        input = input.replaceAll("\u0653", "");//ARABIC MADDAH ABOVE
        input = input.replaceAll("\u0654", "");//ARABIC HAMZA ABOVE
        input = input.replaceAll("\u0655", "");//ARABIC HAMZA BELOW
        input = input.replaceAll("\u0656", "");//ARABIC SUBSCRIPT ALEF
        input = input.replaceAll("\u0657", "");//ARABIC INVERTED DAMMA
        input = input.replaceAll("\u0658", "");//ARABIC MARK NOON GHUNNA
        input = input.replaceAll("\u0659", "");//ARABIC ZWARAKAY
        input = input.replaceAll("\u065A", "");//ARABIC VOWEL SIGN SMALL V ABOVE
        input = input.replaceAll("\u065B", "");//ARABIC VOWEL SIGN INVERTED SMALL V ABOVE
        input = input.replaceAll("\u065C", "");//ARABIC VOWEL SIGN DOT BELOW
        input = input.replaceAll("\u065D", "");//ARABIC REVERSED DAMMA
        input = input.replaceAll("\u065E", "");//ARABIC FATHA WITH TWO DOTS
        input = input.replaceAll("\u065F", "");//ARABIC WAVY HAMZA BELOW
        input = input.replaceAll("\u0670", "");//ARABIC LETTER SUPERSCRIPT ALEF

//        //Replace Waw Hamza Above by Waw
//        input=input.replaceAll("\u0624", "\u0648");
//
//        //Replace Ta Marbuta by Ha
//        input=input.replaceAll("\u0629", "\u0647");
//
//        //Replace Ya
//        // and Ya Hamza Above by Alif Maksura
//        input=input.replaceAll("\u064A", "\u0649");
//        input=input.replaceAll("\u0626", "\u0649");
//        
//        // Replace Alifs with Hamza Above/Below
//        // and with Madda Above by Alif
//        input=input.replaceAll("\u0622", "\u0627");
//        input=input.replaceAll("\u0623", "\u0627");
//        input=input.replaceAll("\u0625", "\u0627");
        input = Normalizer.normalize(input, Normalizer.Form.NFKD)
                .replaceAll("\\p{M}", "");
        return input;
    }

    /**
     * @return the output
     */
    public String getOutput() {
        return output;
    }
}
