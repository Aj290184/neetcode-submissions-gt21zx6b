class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        // Step 1: Find Peak
        int left = 0, right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        int peak = left;

        // Step 2: Search in left (ascending)
        int res = binarySearch(mountainArr, 0, peak, target, true);
        if (res != -1) return res;

        // Step 3: Search in right (descending)
        return binarySearch(mountainArr, peak + 1, n - 1, target, false);
    }

    private int binarySearch(MountainArray arr, int left, int right, int target, boolean asc) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            int val = arr.get(mid);

            if (val == target) return mid;

            if (asc) {
                if (val < target) left = mid + 1;
                else right = mid - 1;
            } else {
                if (val < target) right = mid - 1;
                else left = mid + 1;
            }
        }

        return -1;
    }
}