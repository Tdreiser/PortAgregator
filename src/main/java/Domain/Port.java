package Domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shlokov Andrey
 */
public class Port {
    private final String[] indexes;

    public Port(String[] indexes) {
        validateIndexes(indexes);
        this.indexes = indexes;
    }

    public Integer[][] getCombinations() {
        List<Integer[]> result = new ArrayList<>();
        generatePermutations(getGroups(), result, 0, new Integer[0]);
        return result.toArray(new Integer[0][0]);
    }

    private void generatePermutations(Integer[][] lists, List<Integer[]> result, int depth, Integer[] current) {
        if (depth == lists.length) {
            result.add(current);
            return;
        }
        for (int i = 0; i < lists[depth].length; i++) {
            List<Integer> cur = Arrays.stream(current).collect(Collectors.toList());
            cur.add(lists[depth][i]);
            generatePermutations(lists, result, depth + 1, cur.toArray(new Integer[0]));
        }
    }

    public Integer[][] getGroups() {
        Integer[][] groups = new Integer[indexes.length][0];
        for (int idx = 0; idx < indexes.length; idx++) {
            String[] stringGroups = indexes[idx].split(",");
            Integer[] indexesGroups = new Integer[0];
            for (String stringGroup : stringGroups) {
                Integer[] tail = convertEnumerationToArray(stringGroup);
                indexesGroups = Stream.concat(Arrays.stream(indexesGroups), Arrays.stream(tail))
                        .toArray((size -> (Integer[]) Array.newInstance(Integer[].class.getComponentType(), size)));
            }
            groups[idx] = indexesGroups;
        }
        return groups;
    }

    private Integer[] convertEnumerationToArray(String enumeration) {
        if (enumeration.contains("-")) {
            String[] range = enumeration.split("-");
            int leftBorder = Integer.parseInt(range[0]);
            int rightBorder = Integer.parseInt(range[1]);
            Integer[] result;
            if (Integer.compare(leftBorder, rightBorder) > 1) {
                int buffer = leftBorder;
                leftBorder = rightBorder;
                rightBorder = buffer;
            }
            result = new Integer[rightBorder - leftBorder + 1];
            for (int number = leftBorder, idx = 0; number <= rightBorder; number++, idx++) {
                result[idx] = number;
            }
            return result;
        }
        return new Integer[]{Integer.parseInt(enumeration)};
    }

    private boolean isValidRange(String rangeExpression) {
        if (rangeExpression == null)
            return false;
        if (rangeExpression.contains("-")) {
            String[] range = rangeExpression.split("-");
            return range.length == 2 && isNumber(range[0]) && isNumber(range[1]);
        }
        return isNumber(rangeExpression);
    }

    private void validateIndexes(String[] indexes) {
        for (String index : indexes)
            for (String expression : index.split(","))
                if (!isValidRange(expression)) {
                    throw new IllegalArgumentException("Range or port number " + expression + " is not valid! Please check port range");
                }
    }

    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {

            return false;
        }
        return true;
    }
}
