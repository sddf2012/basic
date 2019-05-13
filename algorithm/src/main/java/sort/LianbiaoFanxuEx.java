package sort;

/**
 * @author liu peng bo
 * @date 2019/3/15
 */
public class LianbiaoFanxuEx {
    static class Node {
        int i;
        Node next;

        public int getI() {
            return i;
        }

        public Node(int i, Node next) {
            this.i = i;
            this.next = next;
        }
    }

    public static Node sort(Node node) {
        Node prev = node;
        Node current = prev.next;
        prev.next = null;

        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void sout(Node node) {
        while (node != null) {
            System.out.print(node.getI() + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);
        Node node5 = new Node(5, node4);
        sout(node5);
        Node node=sort(node5);
        sout(node);
    }
}
