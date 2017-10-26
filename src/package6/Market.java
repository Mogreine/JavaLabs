package package6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Market {

    static class pair<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<pair<T1, T2>>{
        T1 first;
        T2 second;

        public pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(pair<T1, T2> o) {
            return first.compareTo(o.first) != 0 ? first.compareTo(o.first) : second.compareTo(o.second);
        }
    }

    public static void main(String[] args) {
        int availableDocs = 0;
        StringBuilder tempDocs = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            availableDocs = Integer.parseInt(in.readLine());
            for (int i = 0; i < availableDocs; i++) {
                tempDocs.append(in.readLine()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringTokenizer docs = new StringTokenizer(tempDocs.toString()," ");
        TreeSet<String> requiredDocs = new TreeSet<>();
        TreeSet<String> students = new TreeSet<>();
        TreeSet<pair<String, String>> ss = new TreeSet<>();
        int uniqueDocs = 0;
        for (int i = 0; i < availableDocs; i++) {
            String doc = docs.nextToken();
            String student = docs.nextToken();
            requiredDocs.add(doc);
            students.add(student);
            if (ss.add(new pair<>(doc, student))) {
                uniqueDocs++;
            }
        }
        int missingDocs = requiredDocs.size() * students.size() - uniqueDocs;
        System.out.print(missingDocs);
    }

}