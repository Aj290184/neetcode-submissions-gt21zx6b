public class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString();
    }

    private void dfsSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }

        sb.append(node.val).append(",");
        dfsSerialize(node.left, sb);
        dfsSerialize(node.right, sb);
    }

    public TreeNode deserialize(String data) {
        String[] arr = data.split(",");
        int[] index = new int[1];
        return dfsDeserialize(arr, index);
    }

    private TreeNode dfsDeserialize(String[] arr, int[] index) {
        if (arr[index[0]].equals("null")) {
            index[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(arr[index[0]++]));
        node.left = dfsDeserialize(arr, index);
        node.right = dfsDeserialize(arr, index);

        return node;
    }
}