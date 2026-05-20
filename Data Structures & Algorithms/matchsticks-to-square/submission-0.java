class Solution {

    public boolean makesquare(int[] matchsticks) {

        int sum = 0;

        for (int stick : matchsticks) {
            sum += stick;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int side = sum / 4;

        Arrays.sort(matchsticks);

        int[] sides = new int[4];

        return solve(matchsticks, matchsticks.length - 1, sides, side);
    }

    public boolean solve(int[] matchsticks, int index, int[] sides, int target) {

        if (index < 0) {

            return sides[0] == target && sides[1] == target &&
            sides[2] == target && sides[3] == target;
        }

        int stick = matchsticks[index];

        for (int i = 0; i < 4; i++) {

            if (sides[i] + stick > target) {
                continue;
            }

            sides[i] += stick;

            if (solve(matchsticks, index - 1, sides, target)) {
                return true;
            }

            sides[i] -= stick;
        }

        return false;
    }
}