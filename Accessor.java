import java.util.List;

public class Accessor implements Levenshtein {

    int[][] simpleCache;

    int keyLength;
    int wkLength;

    List<String> keywords;

    public Accessor(List<String> keywords) {
        this.keywords = keywords;
    }

    public String wordSuggestion(String work) {

        wkLength = work.length();
        String minstr = "";
        int min = Integer.MAX_VALUE;
        for (String key : keywords) {
            
            
            keyLength = key.length();
            simpleCache = new int[keyLength + 1][wkLength + 1];

            for (int i = 0; i < keyLength + 1; i++) {
                for (int j = 0; j < wkLength + 1; j++) {
                    simpleCache[i][j] = -1;
                }
            }
            
            int dst = levDistanceDp(key, work, keyLength, wkLength);

            if (dst < min) {
                min = dst;
                minstr = key;
            }
        }

        return minstr;

    }


    // Recursive Levenshtein 
    public int levDistance(String key, String work) {

        int keyLastIndex = key.length() - 1;
        int wkLastIndex = work.length() - 1;

        if (key.length() == 0) {
            return work.length();
        }
        if (work.length() == 0) {
            return key.length();
        }
        if (key.equals(work)) {
            return 0;
        }

        if (key.charAt(keyLastIndex) == work.charAt(wkLastIndex)) {
            return levDistance(key.substring(0, keyLastIndex), work.substring(0, wkLastIndex));
        }
        return 1 + (Math.min(levDistance(key.substring(0, keyLastIndex), work),
                Math.min(levDistance(key, work.substring(0, wkLastIndex)),
                        levDistance(key.substring(0, keyLastIndex), work.substring(0, wkLastIndex)))));
    }

    // Optimised Levenshtein using a simple cache

    public int levDistanceDp(String key, String work, int keylen, int worklen) {

        if (simpleCache[keylen][worklen] != -1) {
            return simpleCache[keylen][worklen];
        }
        if (keylen == 0) {
            simpleCache[keylen][worklen] = worklen;
            return simpleCache[keylen][worklen];
        }
        if (worklen == 0) {
            simpleCache[keylen][worklen] = keylen;
            return simpleCache[keylen][worklen];
        }

        if (key.charAt(keylen - 1) == work.charAt(worklen - 1)) {
            simpleCache[keylen][worklen] = levDistanceDp(key, work, keylen - 1, worklen - 1);
            return simpleCache[keylen][worklen];
        }
        simpleCache[keylen][worklen] = 1 + (Math.min(levDistanceDp(key, work, keylen - 1, worklen),
                Math.min(levDistanceDp(key, work, keylen, worklen - 1),
                        levDistanceDp(key, work, keylen - 1, worklen - 1))));
        return simpleCache[keylen][worklen];
    }

}
