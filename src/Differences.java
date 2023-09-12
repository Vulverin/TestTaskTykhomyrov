import java.util.ArrayList;

public class Differences {

    public static void main(String[] args) throws Exception {
        triangleNum("12 14 16 18 20");
    }

    public static void triangleNum(String nums) throws Exception {
        ArrayList<Integer> numbers = new ArrayList<>();
        for(String line: nums.split(" ")){
            numbers.add(Integer.valueOf(line));
        }

        if(numbers.size()>10){
            throw new Exception("One line cannot contain more than 10 numbers");
        }

        diferenceAndTriangle(numbers);
    }

    private static void diferenceAndTriangle(ArrayList<Integer> numList) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        lists.add(numList);
        int size = numList.size()+3;

        boolean zeroList = false;
        lists.add(new ArrayList<>());
        while (!zeroList) {
            for (int j = 0; j < lists.size(); j++) {
                for (int i = 1; i < lists.get(j).size(); i++) {
                    lists.get(j + 1).add(lists.get(j).get(i) - lists.get(j).get(i - 1));

                }

                boolean containsOnlyZeros = true;
                for (int i = 0; i < lists.get(j+1).size(); i++) {
                    if (lists.get(j+1).get(i) != 0) {
                        containsOnlyZeros = false;
                        break;
                    }
                }
                if (containsOnlyZeros) {
                    zeroList = true;
                    break;
                } else {
                    lists.add(new ArrayList<>());
                }
            }
        }

        for (int i=lists.get(lists.size()-2).size(); i< numList.size()+3; i++){
            lists.get(lists.size()-2).add(lists.get(lists.size()-2).get(0));
        }

        for(int j=lists.size()-2; j>0; j--){
            for (int i = lists.get(j-1).size(); i <size; i++) {
                lists.get(j -1).add(lists.get(j).get(i-1) + lists.get(j-1).get(i-1));
            }
        }

        for (int i=lists.get(0).size()-3; i<lists.get(0).size(); i++){
            System.out.print(lists.get(0).get(i)+" ");
        }
    }
}

