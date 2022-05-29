package com.example.spl1_t2c;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    private static final int  w = 256;
    private TrieNode root;

    private class TrieNode {
        boolean isLeaf;
        TrieNode[] children;

        public TrieNode() {
            isLeaf = true;
            children = new TrieNode[w];
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for(int i=0, L=word.length(); i<L; i++) {
            int id = word.charAt(i) - 0;
                if(String.valueOf(root) == String.valueOf(root.children[i])){
                    if(current.children[id]==null) {
                        current.children[id] = new TrieNode();
                        current.children[id].isLeaf = false;
                    }
                    current = current.children[id];
                }
//            if(word.contains("+") || word.contains("-") ||word.contains("*") || word.contains("/")  ){
//               BinEx be = new BinEx();
//               be.expTree(word);
//            }
            if(current.children[id]==null) {
                current.children[id] = new TrieNode();
                current.children[id].isLeaf = false;
            }
            current = current.children[id];
        }
        current.isLeaf = true;
    }

    public boolean contains(String word) {
        return search(word, 1);
    }

    public boolean containsPrefix(String prefix) {
        return search(prefix, 2);
    }

    private boolean search(String str, int type) {
        TrieNode current = root;
        int i=-1, L=str.length();
        while(++i<L) {
            int id = str.charAt(i) - 0;

            if(current.children[id]==null) return false;
            current = current.children[id];
        }
        return type==1 ? current.isLeaf : true;
    }

    public List<String> listWords() {
        List<String> list = new ArrayList<>();
        list(root, 0, "", list);
        return list;
    }
    private void list(TrieNode current, int id, String prefix, List<String> list) {
        if(current==null) return;
        for(int i=0; i<w; i++) {
            TrieNode child = current.children[i];
            if(child!=null) {
                String res = prefix + (char)i;
                if(child.isLeaf ) list.add(res);
                list(child, i, res, list);
            }
        }
    }
    public void print() {
        print("", root, 0, true, true);
    }

    private void print(String prefix, TrieNode root, int id, boolean isTail, boolean isRoot) {

        if(!isRoot) {
             System.out.println(prefix
                    + (isTail ? "└── " : "├── ")
                    + (char)id
                    + (root.isLeaf ? " ***" : ""));

        }

        TrieNode lastChild = null;
        int lastChildId = 0;
        boolean isLastChild = true;
        for (int i=w-1; i>=0; i--) {
            if(root.children[i]!=null) {
                if(isLastChild) {
                    isLastChild = false;
                    lastChild = root.children[i];
                    lastChildId = i;
                }
                else print(prefix + (isRoot ? "" : (isTail ? "    " : "│   ")),
                        root.children[i], i, false, false);

            }
        }
        if (lastChild!=null) {

            print(prefix + (isRoot ? "" : (isTail ?"    " : "│   ")),
                    lastChild, lastChildId, true, false);

        }
        //System.out.println(lastChildId + " ---_---_-" );
    }
}
