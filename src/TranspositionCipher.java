import java.util.ArrayList;

public class TranspositionCipher {

    public static void encrypt(String plaintext) throws Exception {
        for(String line: plaintext.split("\n"))
            encryptOneString(line);
        }
    private static void encryptOneString(String plaintext) throws Exception {
        StringBuilder encryptedText = new StringBuilder();

        boolean nextDi = true;
        ArrayList<Integer> code = new ArrayList<>();

        String numbersStr = plaintext.substring(0, plaintext.indexOf(' '));
        for (int i = 0; i < numbersStr.length(); i++) {
            char digitChar = numbersStr.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            code.add(digit);
        }

        if(code.size()>9){
            throw new Exception("The maximum key length is 9");
        }

        String text = plaintext.substring(plaintext.indexOf(' ')+1);
        ArrayList<String> diAndSi = new ArrayList<>();
        for (int i = 0; i < text.length();) {
            if (i + 1 < text.length() && nextDi) {
                diAndSi.add(new String(new char[]{text.charAt(i), text.charAt(i + 1)}));
                nextDi=false;
                i += 2;
            } else {
                diAndSi.add(String.valueOf(text.charAt(i)));
                nextDi=true;
                i++;
            }
        }

        for (int i=1; i<code.size()+1; i++){
            for(int j=code.indexOf(i); j<diAndSi.size();j+=code.size()){
                    encryptedText.append(diAndSi.get(j));
            }
        }

        System.out.println(encryptedText);
    }

    public static void main(String[] args) throws Exception {
        String plaintext = "3412 THISISJUSTATEST";
        encrypt(plaintext);
    }
}