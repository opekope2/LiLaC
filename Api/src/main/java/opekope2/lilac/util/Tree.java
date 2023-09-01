package opekope2.lilac.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

/**
 * Container class for a tree's {@link Node nodes} and a {@link Formatter tree formatter},
 * which can pretty-print a tree as a String.
 */
public final class Tree {
    private Tree() {
    }

    /**
     * Represents a node of a tree.
     *
     * @implNote a Node itself is a tree
     */
    public static final class Node {
        @NotNull
        private final List<Node> _children = new ArrayList<>();
        /**
         * The text associated with the node.
         */
        @NotNull
        public final String text;
        /**
         * A read-only list of the node's children.
         */
        @NotNull
        public final List<Node> children = Collections.unmodifiableList(_children);

        /**
         * Creates a new node with its {@link #text associated text} and an optional parent.
         *
         * @param text   The text to associate with the node
         * @param parent The optional parent of the node.
         *               Passing any non-null value will add the created node to the parent's {@link #children}
         */
        public Node(@NotNull String text, @Nullable Node parent) {
            this.text = text;
            if (parent != null) {
                parent._children.add(this);
            }
        }

        /**
         * Creates a new node with its {@link #text associated text} without a parent.
         *
         * @param text The text to associate with the node
         */
        public Node(@NotNull String text) {
            this(text, null);
        }

        /**
         * Creates a new node and adds it as a new child of the current node.
         *
         * @param text The text to associate with the node
         * @return The newly created node
         */
        @NotNull
        public Node appendChild(@NotNull String text) {
            return new Node(text, this);
        }
    }

    /**
     * Utility class to pretty-print a tree.
     */
    public static final class Formatter {
        private final StringBuilder builder = new StringBuilder();
        private final Stack<Boolean> styles = new Stack<>() {
            private final BitSet bits = new BitSet();
            private int index = -1;

            @Override
            public Boolean push(Boolean item) {
                bits.set(++index, item);
                return item;
            }

            @Override
            public synchronized Boolean pop() {
                if (index < 0) throw new IndexOutOfBoundsException(index);
                return bits.get(index--);
            }

            @Override
            public synchronized Boolean get(int index) {
                return bits.get(index);
            }

            @Override
            public synchronized int size() {
                return index + 1;
            }
        };

        /**
         * Indents the current formatter, then runs the given function, then un-indents the current formatter.
         *
         * @param function The function to execute while indented
         */
        public void indent(@NotNull Consumer<Formatter> function) {
            styles.push(true);
            function.accept(this);
            styles.pop();
        }

        /**
         * Writes the node at the current indention, without its children.
         *
         * @param node      The node to write
         * @param lastChild Whether the given node is the last node of its parent node.
         *                  Should be {@code true}, if the node is a root node
         */
        public void appendNode(@NotNull Node node, boolean lastChild) {
            if (lastChild) {
                styles.pop();
                styles.push(false);
            }

            for (int i = 1, size = styles.size(); i < size - 1; i++) {
                builder.append(styles.get(i) ? "│ " : "  ");
            }

            builder.append(styles.size() == 1 ? "" : (lastChild ? "└─" : "├─"));
            builder.append(node.text).append(System.lineSeparator());
        }

        /**
         * Writes the node at the current indention, including its children.
         *
         * @param node      The node to write
         * @param lastChild Whether the given node is the last node of its parent node.
         *                  Should be {@code true}, if the node is a root node
         */
        public void append(@NotNull Node node, boolean lastChild) {
            indent(formatter -> {
                formatter.appendNode(node, lastChild);

                var it = node.children.iterator();
                while (it.hasNext()) {
                    var next = it.next();
                    var last = !it.hasNext();

                    append(next, last);
                }
            });
        }

        /**
         * Pretty-prints the given node.
         *
         * @param node The tree to pretty-print
         */
        public static String format(@NotNull Node node) {
            Formatter formatter = new Formatter();
            formatter.append(node, true);
            return formatter.toString();
        }

        @Override
        public String toString() {
            return builder.toString();
        }
    }
}
